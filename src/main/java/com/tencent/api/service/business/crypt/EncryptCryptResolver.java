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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bobzbfeng
 * 加密
 */
public class EncryptCryptResolver extends AbstractCryptResolver{

    private static Logger LOG = LoggerFactory.getLogger(EncryptCryptResolver.class);

    private static final int VERIFY_TYPE = 1;

    @Override
    public boolean support(int type) {
        return type == VERIFY_TYPE;
    }

    @Override
    protected CryptResponseDto subResolve(CryptFormDto formDto, SE se) {
        final String hid = formDto.getHid();
        if (updateDownCounter(formDto.getHid(), se.getDownCounter())) {
            LOG.debug("SE {} upCounter was updated to {}",se,se.getDownCounter());

            String encryptedData = Hex.toHexString(crypto.encryptData(se.finalMasterKeyName(), se.getSymmetricType(),
                    Hex.toByteArray(String.format("%08X", se.getDownCounter())
                            + String.format("%02X", formDto.getData().length() / 2) + formDto.getData()),
                    Hex.toByteArray( se.getDownSeed()), Hex.toByteArray( se.getMacSeed())));

            resp.setErr(0);
            resp.setData(encryptedData);
            AuditLog.createEncryptAuditLog(hid,loadCurrentApplicationUuid(),"加密成功");
        }
        return resp;
    }

}