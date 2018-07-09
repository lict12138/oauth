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

/**
 * @author bobzbfeng
 *  签名验签
 */
public class VerifyCryptResolver extends AbstractCryptResolver{

    private static Logger LOG = LoggerFactory.getLogger(VerifyCryptResolver.class);

    private static final int VERIFY_TYPE = 0;

    @Override
    public boolean support(int type) {
        return type == VERIFY_TYPE;
    }


    @Override
    public CryptResponseDto subResolve(CryptFormDto formDto,SE se) {

        String upCounter = formDto.getResult().substring(0, 8);
        String message = "44" + upCounter + formDto.getData();
        String signMes = formDto.getResult().substring(8);
        String publicKey = "04" + se.getEccpubkey();
        boolean isSM = se.getAsymmetricType() == 1;

        System.out.println("[VERIFY] Verify Hid "+se.getHid()+" by message {"+message+"}");
        System.out.println("[VERIFY] Verify Hid "+se.getHid()+" by signMes {"+signMes+"}");
        System.out.println("[VERIFY] Verify Hid "+se.getHid()+" by publicKey {"+publicKey+"}");
        System.out.println("[VERIFY] Verify Hid "+se.getHid()+" by isSM {"+isSM+"}");

        if (!crypto.Verifyex(Hex.toByteArray(message),
                Hex.toByteArray(signMes), Hex.toByteArray(publicKey),isSM
                )) {
            resp.setData("Verify failed.");
            resp.setErr(1);
            AuditLog.createVerifyAuditLog(se.getHid(),loadCurrentApplicationUuid(),"验签失败");

            System.out.println("[VERIFY] Verify Hid "+se.getHid()+" result is FAILED");
            return resp;
        }
        if (upCounterHandler.insert(formDto.getHid(),upCounter)) {
            AuditLog.createVerifyAuditLog(se.getHid(),loadCurrentApplicationUuid(),"验签成功{counter: "+upCounter+"}");
            System.out.println("[VERIFY] Verify Hid "+se.getHid()+" result is SUCCESS");
            resp.setErr(0);
        } else {
            AuditLog.createVerifyAuditLog(se.getHid(),loadCurrentApplicationUuid(),"验签失败{upCounter:"+upCounter+"已过期}");
            System.out.println("[VERIFY] Verify Hid "+se.getHid()+" result is FAILED");
            resp.setData("upCounter exist.");
            resp.setErr(2);
        }
        return resp;
    }
}