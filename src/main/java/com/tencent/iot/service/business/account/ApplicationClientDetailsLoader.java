/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.business.account;

import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.domain.Application;
import com.tencent.iot.infrastructure.impl.ApplicationRepository;
import com.tencent.iot.service.dto.ApplicationClientDetailsDto;
import com.tencent.oauth.domain.oauth.OauthClientDetails;
import com.tencent.oauth.domain.oauth.OauthClientRepository;
import org.apache.commons.lang.StringUtils;

/**
 * @author bobzbfeng
 */
public class ApplicationClientDetailsLoader {

    private transient ApplicationRepository applicationRepository = BeanProvider.getBean(ApplicationRepository.class);

    private transient OauthClientRepository oauthClientRepository = BeanProvider.getBean(OauthClientRepository.class);

    private String uuid;

    public ApplicationClientDetailsLoader(String uuid){
        this.uuid = uuid;
    }

    public ApplicationClientDetailsDto load(){
        Application application = applicationRepository.findApplicationByUuid(uuid);
        if(null == application || StringUtils.isEmpty(application.getClientId())){
            return null;
        }

        OauthClientDetails oauthClientDetails = oauthClientRepository.findOauthClientDetails(application.getClientId());
        return new ApplicationClientDetailsDto(oauthClientDetails,application);
    }
}