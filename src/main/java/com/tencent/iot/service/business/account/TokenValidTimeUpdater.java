/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.business.account;

import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.service.dto.ApplicationClientDetailsDto;
import com.tencent.iot.service.dto.ResultDto;
import com.tencent.oauth.domain.oauth.OauthClientDetails;
import com.tencent.oauth.domain.oauth.OauthClientRepository;
import com.tencent.oauth.domain.security.SecurityUtils;
import com.tencent.oauth.infrastructure.oauth.OAuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bobzbfeng
 */
public class TokenValidTimeUpdater {

    private static Logger LOG = LoggerFactory.getLogger(TokenValidTimeUpdater.class);

    private transient OauthClientRepository oauthClientRepository = BeanProvider.getBean(OauthClientRepository.class);

    private ApplicationClientDetailsDto formDto;

    public TokenValidTimeUpdater(ApplicationClientDetailsDto formDto){
        this.formDto = formDto;
    }

    public ResultDto update(){
        final String clientId  = formDto.getClientId();
        OauthClientDetails oauthClientDetails = oauthClientRepository.findOauthClientDetails(clientId);
        if(null==oauthClientDetails){
            return new ResultDto("请求失败，参数有误");
        }

        int validTime = formDto.getValidTime();
        oauthClientDetails.accessTokenValidity(validTime* OAuthUtils.DIVISOR_SECONDS);
        oauthClientRepository.updateTokenTime(oauthClientDetails);
        LOG.debug("{} has updated ClientDetails TOKEN ValidTime to {}", SecurityUtils.currUser().getUsername(),oauthClientDetails.accessTokenValidity());
        return new ResultDto(true);
    }
}