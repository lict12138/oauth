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
public class EncryptHandler extends AbstractRPCClient{

    public EncryptHandler(){
        initialParams();
    }

    public byte[] handle(int type, byte[] data, byte[] downSeed, byte[] macSeed){
        try {
            String result =  client.encrypt((byte)type, Hex.toHexString(data),Hex.toHexString(downSeed),Hex.toHexString(macSeed));
            return Hex.toByteArray(result);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
}