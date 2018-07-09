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
public class SignHandler extends AbstractRPCClient{

    public SignHandler(){
        initialParams();
    }

    public byte[] handle(byte[] message, String privateKey){
        try {
            try {
                String result =  client.Sign(Hex.toHexString(message), privateKey);
                return Hex.toByteArray(result);
            } catch (TException e) {
                e.printStackTrace();
            }
        }finally {
            close();
        }
        return null;
    }
}