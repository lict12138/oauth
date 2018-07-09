package com.tencent.api.service.business.utils;

import com.tencent.tusi.sgx.*;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.testng.annotations.Test;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.*;

public class CryptoTest {

    private String address = "127.0.0.1";
    // private String address = "192.168.17.128";


    private int	port	= 8191;
    private int	timeout	= 100 * 1000;

    @Test(enabled = false)
    public void testCrypto(){
        TTransport transport = new TFramedTransport(new TSocket(address, port, timeout));
        TProtocol protocol = new TCompactProtocol(transport);
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol, "cryptoService");
        RPCCrypto.Client client = new RPCCrypto.Client(mp1);
        try {
            open(transport);

            // 111
            List<Map<String, KeyInfo>> keysInfo = client.getKeysInfo();
            for (Map<String, KeyInfo> map : keysInfo) {
                Set<String> ks = map.keySet();
                Iterator<String> it = ks.iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    KeyInfo value = map.get(key);
                    System.out.println(key + "," + value.type + "," + value.key);
                }
            }
            String message = "11";
            String sig = client.Sign(message, "04E91F01DF");
            System.out.println(client.Verify(message, sig, "04E91F01DF"));

            sig = client.Sign(message, "0410C26685");
            System.out.println(client.Verify(message, sig, "0410C26685"));

            String hid = "0186171200000024";
            String data = "11";
            String sm2Pub = "0409F9DF311E5421A150DD7D161E4BC5C672179FAD1833FC076BB08FF356F35020CCEA490CE26775A52DC6EA718CC1AA600AED05FBF35E084A6632F6072DA9AD13";
            // String sm2Pri =
            // "3945208F7B2144B13F36E38AC6D39F95889393692860B51A42FB81EF4DF7C5B8";
            String rsaPub = "9136946C59400263C4D6C5260D383D825BE422D9CD8C62310DBD5D76F15990D218519EA4E727932297E1D6746BDBD8F9DC9AF8C51E936A277E3A77E107A86712A834F751BAD08BB92C75EE127842D6DE7F6DE9486EF62D5B6A96F8B269F2FABDD8BC081063D7476B664FBBF4DC4C315E83E445673BBD159F9E52D210C8DE87A5B7E8E83FA8F6C1F7E9DE0F52EBD7B26F";
            String rsaPri = "7F161157B1D47F42E9A63CEC4C6D88076A7C82B3B35CC522C12F9578DF48CFA1CF7EBF24FAD87BC0AEC40B77E05D8BCB40F2AFFD7400F7CA90828320021FB3F57418C6CA5480E786589849FCDD4FFA22313EB13A730F1EBD96505002ECD33D210780B55707AA74186C351C2D9518A5B25285890408C99B28FF256E20AC029855DC5CA740C53285754BE6D8B457262541";
            byte[] upDownMacKey;
            String out;

            byte[] _upKey = new byte[16];
            byte[] _downKey = new byte[16];
            byte[] _macKey = new byte[16];

            SEKey seKey = client.requestSEKey("masterkey", hid, sm2Pub);
            seKey = client.requestSEKey("masterkey", hid, rsaPub);
            upDownMacKey = decodeKey(Hex.toByteArray(seKey.encryptedKey), rsaPub, rsaPri);
            System.arraycopy(upDownMacKey, 0, _upKey, 0, 16);
            System.arraycopy(upDownMacKey, 16, _downKey, 0, 16);
            System.arraycopy(upDownMacKey, 32, _macKey, 0, 16);

            out = client.encryptData("masterkey", (byte) 0, data, seKey.downSeed, seKey.macSeed);
            if (client.decrypt((byte) 0, out, Hex.toHexString(_downKey), Hex.toHexString(_macKey)).equals(data))
                throw new RuntimeException("ee");
            out = client.encryptData("masterkey", (byte) 1, data, seKey.downSeed, seKey.macSeed);
            if (client.decrypt((byte) 1, out, Hex.toHexString(_downKey), Hex.toHexString(_macKey)).equals(data))
                throw new RuntimeException("ee");
            out = client.encryptData("masterkey", (byte) 2, data, seKey.downSeed, seKey.macSeed);
            if (client.decrypt((byte) 2, out, Hex.toHexString(_downKey), Hex.toHexString(_macKey)).equals(data))
                throw new RuntimeException("ee");

            out = client.encrypt((byte) 0, data, Hex.toHexString(_upKey), Hex.toHexString(_macKey));
            if (client.decryptData("masterkey", (byte) 0, out, seKey.upSeed, seKey.macSeed).equals(data))
                throw new RuntimeException("ee");
            out = client.encrypt((byte) 1, data, Hex.toHexString(_upKey), Hex.toHexString(_macKey));
            if (client.decryptData("masterkey", (byte) 1, out, seKey.upSeed, seKey.macSeed).equals(data))
                throw new RuntimeException("ee");
            out = client.encrypt((byte) 2, data, Hex.toHexString(_upKey), Hex.toHexString(_macKey));
            if (client.decryptData("masterkey", (byte) 2, out, seKey.upSeed, seKey.macSeed).equals(data))
                throw new RuntimeException("ee");
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            close(transport);
        }
    }

    public void open(TTransport transport) {
        if (transport != null && !transport.isOpen()) {
            try {
                transport.open();
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(TTransport transport) {
        if (transport != null && transport.isOpen()) {
            transport.close();
        }
    }

    private static byte[] decodeKey(byte[] encryptedKey, String rsaPub, String rsaPri) {
        try {
            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(
                    new BigInteger(1, Hex.toByteArray("00" + rsaPub)), new BigInteger(1, Hex.toByteArray(rsaPri)));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(rsaPrivateKeySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(encryptedKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}