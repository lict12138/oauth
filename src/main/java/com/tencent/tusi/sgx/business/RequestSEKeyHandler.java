/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.tusi.sgx.business;

import com.tencent.tusi.sgx.Hex;
import com.tencent.tusi.sgx.SEKey;
import org.apache.thrift.TException;

/**
 * @author bobzbfeng
 */
public class RequestSEKeyHandler extends AbstractRPCClient{

    public RequestSEKeyHandler(){
        initialParams();
    }

    public SEKey handle(String masterKey, String hid, byte[] pubKey){

            try {
                return client.requestSEKey(masterKey,hid, Hex.toHexString(pubKey));
            } catch (TException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        return null;

    }
}