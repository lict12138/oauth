package com.tencent.commons.utils;

/**
 * 2016/2/17
 *
 * @author bobzbfeng
 */


import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

@SuppressWarnings("restriction")
public abstract class IdsBase64Utils {

    public final static String KEY = "mhnY0408ucickihc";

    private static final String ENCODING = "UTF-8";
    private static final Charset DEFAULT_CHARSET = Charset.forName(ENCODING);


    private IdsBase64Utils() {
    }


    public static String base64Encode(String username, String password) {
        String text = username + ":" + password;
        return base64Encode(text);
    }

    public static String base64Encode(String text) {
        return Base64Utils.encodeToString(text.getBytes(DEFAULT_CHARSET));
    }


    public static String base64Decode(String decodeText) {
        final byte[] decode = Base64Utils.decodeFromString(decodeText);
        return new String(decode);
    }


    public static String encrypt(String sSrc) {
//        if (KEY == null) {
//            System.out.print("Key为空null");
//            return null;
//        }
//        // 判断Key是否为16位
//        if (KEY.length() != 16) {
//            System.out.print("Key长度不是16位");
//            return null;
//        }
        try {
            byte[] raw = KEY.getBytes(ENCODING);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(ENCODING));

            //此处使用BASE64做转码功能，同时能起到2次加密的作用。
            return Base64Utils.encodeToString(encrypted);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    // 解密
    public static String decrypt(String sSrc) {
        try {
//            // 判断Key是否正确
//            if (KEY == null) {
//                System.out.print("Key为空null");
//                return null;
//            }
//            // 判断Key是否为16位
//            if (KEY.length() != 16) {
//                System.out.print("Key长度不是16位");
//                return null;
//            }
            byte[] raw = KEY.getBytes(ENCODING);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64Utils.decodeFromString(sSrc);//先用base64解密

            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, ENCODING);

        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
