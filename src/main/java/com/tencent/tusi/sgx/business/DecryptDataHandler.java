/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.tusi.sgx.business;

import com.tencent.tusi.sgx.Hex;
import org.apache.thrift.TException;

/**
 * @author bobzbfeng
 */
public class DecryptDataHandler extends AbstractRPCClient{

    public DecryptDataHandler(){
        initialParams();
    }

    public byte[] handle(String masterKey, int type, byte[] data, byte[] upSeed, byte[] macSeed){
        try {
            String result =  client.decryptData(masterKey, (byte) type, Hex.toHexString(data),Hex.toHexString(upSeed),Hex.toHexString(macSeed));
            return Hex.toByteArray(result);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
}