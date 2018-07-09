/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.business.system;

import com.tencent.commons.utils.UUIDGenerator;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.domain.Application;
import com.tencent.iot.domain.ApplicationStatus;
import com.tencent.iot.infrastructure.impl.ApplicationRepository;
import com.tencent.oauth.domain.oauth.OauthClientDetails;
import com.tencent.oauth.domain.oauth.OauthClientRepository;
import com.tencent.oauth.domain.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.tencent.oauth.infrastructure.oauth.OAuthUtils.*;

import java.util.UUID;

/**
 * @author bobzbfeng
 */
public class ApplicationApproveHandler {

    private static Logger LOG = LoggerFactory.getLogger(ApplicationApproveHandler.class);

    private transient ApplicationRepository applicationRepository = BeanProvider.getBean(ApplicationRepository.class);

    private transient OauthClientRepository oauthClientRepository = BeanProvider.getBean(OauthClientRepository.class);

    private String uuid;

    private ApplicationStatus status;

    public ApplicationApproveHandler(String uuid,ApplicationStatus status){
        this.uuid = uuid;
        this.status = status;
    }

    public void handle(){
        Application application = applicationRepository.findApplicationByUuid(uuid);
        if(null == application || application.getStatus().equals(status)){
            return;
        }
        application.setStatus(status);
        if(status.equals(ApplicationStatus.APPROVE)){
            String clientId = generateOauthClient();
            application.setClientId(clientId);
        }

        applicationRepository.updateApplicationStatus(application);
        LOG.debug("{} has approved Application {} status to {}", SecurityUtils.currUser().getUsername(),application,status.getLabel());
    }

    private String generateOauthClient() {

        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.clientId(generateClientId());
        oauthClientDetails.clientSecret(generateClientSecret());

        oauthClientDetails.authorizedGrantTypes(APPLICATION_GRANT_TYPES);
        oauthClientDetails.scope(APPLICATION_CLIENT_SCOPE);

        oauthClientDetails.resourceIds(APPLICATION_RESOURCE_IDS);
        oauthClientDetails.authorities(APPLICATION_CLIENT_PRIVILEGES);

        //token 有效期的默认时间为12个小时
        oauthClientDetails.accessTokenValidity(DEFAULT_SECONDS);


        oauthClientRepository.saveOauthClientDetails(oauthClientDetails);

        return oauthClientDetails.clientId();
    }
}