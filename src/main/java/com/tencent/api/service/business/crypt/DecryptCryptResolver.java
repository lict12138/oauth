/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.business.crypt;

import com.tencent.api.service.dto.CryptFormDto;
import com.tencent.api.service.dto.CryptResponseDto;
import com.tencent.iot.domain.AuditLog;
import com.tencent.iot.domain.SE;
import com.tencent.tusi.sgx.Crypto;
import com.tencent.tusi.sgx.Hex;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bobzbfeng
 * 解密
 */
public class DecryptCryptResolver extends AbstractCryptResolver{

    private static final int VERIFY_TYPE = 2;

    @Override
    public boolean support(int type) {
        return type == VERIFY_TYPE;
    }

    @Override
    protected CryptResponseDto subResolve(CryptFormDto formDto, SE se) {

        String hid = formDto.getHid();
        String s = Hex.toHexString(crypto.decryptData(se.finalMasterKeyName(), se.getSymmetricType(), Hex.toByteArray(formDto.getData()),
                Hex.toByteArray( se.getUpSeed()), Hex.toByteArray( se.getMacSeed())));
        int len = Integer.parseInt(s.substring(8, 10), 16);
        s = s.substring(0, 10 + len * 2);
        String upCounter =  s.substring(0, 8);
        if (upCounterHandler.insert(hid,upCounter)) {
            String decryptedData = s.substring(10);
            resp.setErr(0);
            resp.setData(decryptedData);
            AuditLog.createDecryptAuditLog(hid, loadCurrentApplicationUuid(), "解密成功{upCounter :"+upCounter+"}");
        } else {
            resp.setData("upCounter exist.");
            resp.setErr(1);
            AuditLog.createDecryptAuditLog(hid, loadCurrentApplicationUuid(), "解密失败{upCounter: "+upCounter+"已过期}");
        }
        return resp;
    }

}