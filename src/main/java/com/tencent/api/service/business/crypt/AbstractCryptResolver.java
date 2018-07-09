/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.business.crypt;

import com.tencent.api.service.dto.CryptFormDto;
import com.tencent.api.service.dto.CryptResponseDto;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.domain.Application;
import com.tencent.iot.domain.SE;
import com.tencent.iot.infrastructure.impl.ApplicationRepository;
import com.tencent.iot.infrastructure.impl.SeRepository;
import com.tencent.oauth.domain.oauth.OauthClientRepository;
import com.tencent.oauth.domain.security.SecurityUtils;
import com.tencent.tusi.sgx.Crypto;

/**
 * @author bobzbfeng
 */
public abstract class AbstractCryptResolver implements CryptResolver{

    protected transient Crypto crypto = BeanProvider.getBean(Crypto.class);

    protected CryptResponseDto resp = new CryptResponseDto();

    protected UpCounterHandler upCounterHandler = new UpCounterHandler();

    private transient ApplicationRepository applicationRepository = BeanProvider.getBean(ApplicationRepository.class);

    protected transient SeRepository seRepository = BeanProvider.getBean(SeRepository.class);

    @Override
    public CryptResponseDto resolve(CryptFormDto formDto) {
        final String hid = formDto.getHid();
        SE se = seRepository.findSEByHid(hid);
        if(null == se){
            return resp;
        }
        return subResolve(formDto,se);
    }

    /**
     *
     * @param formDto
     * @param se
     * @return CryptResponseDto
     *
     * 将由各子级策略处理各实现逻辑
     */
    protected abstract CryptResponseDto subResolve(CryptFormDto formDto,SE se);


    /*
    更新下行计数器
     */
    protected boolean updateDownCounter(String hid, int downCounter) {
        seRepository.updateSEDownCounterByHid(hid, downCounter);
        return true;
    }

    protected String loadCurrentApplicationUuid(){
        String clientId = SecurityUtils.currentClientId();
        Application application = applicationRepository.findApplicationByClientId(clientId);
        return null == application? null:application.getUuid();
    }
}