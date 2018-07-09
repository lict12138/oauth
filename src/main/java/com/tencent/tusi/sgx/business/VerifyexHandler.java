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
public class VerifyexHandler extends AbstractRPCClient{

    public VerifyexHandler(){
        initialParams();
    }

    public boolean handle(byte[] message, byte[] sig, byte[] publicKey,boolean isSM){

            try {
                return client.Verifyex(Hex.toHexString(message), Hex.toHexString(sig), Hex.toHexString(publicKey), isSM);
            } catch (TException e) {
                return false;
            }finally {
                close();
            }

    }
}