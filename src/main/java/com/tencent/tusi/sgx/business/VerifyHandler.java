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
public class VerifyHandler extends AbstractRPCClient{

    public VerifyHandler(){
        initialParams();
    }

    public boolean handle(byte[] message, byte[] sig, String publicKey){
        try{
            try {
                return client.Verify(Hex.toHexString(message),Hex.toHexString(sig),publicKey);
            } catch (TException e) {
                e.printStackTrace();
            }
        }finally {
            close();
        }
        return false;
    }
}