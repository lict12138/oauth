/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.business.register;

import com.tencent.api.service.dto.RegisterFormDto;
import com.tencent.api.service.dto.RegisterResponseDto;
import com.tencent.commons.utils.IOTHolder;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.domain.AuditLog;
import com.tencent.iot.domain.SE;
import com.tencent.iot.infrastructure.impl.SeRepository;
import com.tencent.tusi.sgx.Crypto;
import com.tencent.tusi.sgx.Hex;
import com.tencent.tusi.sgx.SEKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bobzbfeng
 *         设备激活（注册）的流程
 */
public class SERegister {

    private static Logger LOG = LoggerFactory.getLogger(SERegister.class);

    private transient Crypto crypto = BeanProvider.getBean(Crypto.class);

    private static final int INITIAL_COUNTER = 1;

    private RegisterFormDto formDto;

    private RegisterResponseDto resp = new RegisterResponseDto();

    private transient SeRepository seRepository = BeanProvider.getBean(SeRepository.class);

    public SERegister(RegisterFormDto formDto) {
        this.formDto = formDto;
    }

    public RegisterResponseDto register() {
        final String hid = formDto.getHid();
        SE se = seRepository.findSEByHid(hid);

        int downCounter =null ==se? INITIAL_COUNTER:se.getDownCounter();

        String pubkey = formDto.emptyRsaPubKey() ? "04" + formDto.getEccpubkey() : formDto.getRsapubkey();
        String masterKey = IOTHolder.MASTER_KEY;
        System.out.println("[REGISTER] Register Hid " + hid + " with publicKey {" + pubkey + "}");
        SEKey seKey = crypto.requestSEKey(masterKey, hid, Hex.toByteArray(pubkey));
        if (seKey != null) {

            System.out.println("[REGISTER] Register Hid " + hid + " Response EncryptedKey {" + seKey.getEncryptedKey() + "}");
            String encryptedKey = seKey.getEncryptedKey();

            SE newSe = createNewSE(hid, downCounter, seKey, masterKey);
            if(null == se){
                seRepository.saveSE(newSe);
                LOG.debug("A new SE{} was Inserted to DB", newSe);
                AuditLog.createSERegisterAuditLog(hid, "注册成功");
            }else{
                seRepository.updateSE(newSe);
                LOG.debug("A new SE{} was Updated to DB", newSe);
                AuditLog.createSERegisterAuditLog(hid, "重置成功");
            }

            resp.setEncryptedKey(encryptedKey);
            resp.setDownCounter(String.format("%08X", downCounter));
            resp.setErr(0);
        }else {
            LOG.debug("A new SE{} was Failed in Registering", hid);
        }

        return resp;
    }

    private SE createNewSE(String hid, int downCounter, SEKey seKey, String masterKey) {
        SE se = new SE();
        se.setHid(hid);
        se.setEccpubkey(formDto.getEccpubkey());
        se.setRsapubkey(formDto.getRsapubkey());
        se.setDownCounter(downCounter);
        se.setUpSeed(seKey.getUpSeed());
        se.setDownSeed(seKey.getDownSeed());
        se.setMacSeed(seKey.getMacSeed());
        se.setEncryptedkey(seKey.getEncryptedKey());
        se.setAlgType(formDto.getAlgType());
        se.setMasterKeyName(masterKey);
        return se;
    }
}