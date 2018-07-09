/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.tusi.sgx.impl;

import com.tencent.tusi.sgx.Crypto;
import com.tencent.tusi.sgx.SEKey;
import com.tencent.tusi.sgx.business.*;
import org.springframework.stereotype.Repository;

/**
 * @author bobzbfeng
 */

@Repository("crypto")
public class CryptoRepository implements Crypto{

    @Override
    public byte[] Sign(byte[] message, String privateKey) {
        return new SignHandler().handle(message, privateKey);
    }

    @Override
    public boolean Verify(byte[] message, byte[] sig, String publicKey) {
       return new VerifyHandler().handle(message,sig,publicKey);
    }

    @Override
    public boolean Verifyex(byte[] message, byte[] sig, byte[] pub, boolean isSM) {
        return new VerifyexHandler().handle(message,sig,pub,isSM);
    }

    @Override
    public SEKey requestSEKey(String masterKey,String hid, byte[] pubKey) {
        long before = System.currentTimeMillis();
        SEKey seKey = new RequestSEKeyHandler().handle(masterKey, hid, pubKey);
        long after = System.currentTimeMillis();
        System.out.println("本次注册RPC流程时间为 ["+(after-before)+"] 毫秒");
        return seKey;
    }

    @Override
    public byte[] encryptData(String masterKey, int type, byte[] data, byte[] downSeed, byte[] macSeed) {
        return new EncryptDataHandler().handle(masterKey,type,data,downSeed,macSeed);
    }

    @Override
    public byte[] decryptData(String masterKey, int type, byte[] data, byte[] upSeed, byte[] macSeed) {
        return new DecryptDataHandler().handle(masterKey,type,data,upSeed,macSeed);
    }

    @Override
    public byte[] encrypt(int type, byte[] data, byte[] downSeed, byte[] macSeed) {
        return new EncryptHandler().handle(type,data,downSeed,macSeed);
    }

    @Override
    public byte[] decrypt(int type, byte[] data, byte[] upSeed, byte[] macSeed) {
        return new DecryptHandler().handle(type,data,upSeed,macSeed);
    }
}