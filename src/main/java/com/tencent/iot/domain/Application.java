/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.domain;

import com.tencent.commons.domain.AbstractDomain;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.security.UserRepository;

/**
 * @author bobzbfeng
 */
public class Application extends AbstractDomain{
    private static final long serialVersionUID = 4452770685077666292L;

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private String applicationName;

    private String creatorUuid;

    private String clientId;

    private ApplicationStatus status = ApplicationStatus.SUBMIT;

    private String description;

    public Application(){
        super();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getCreatorUuid() {
        return creatorUuid;
    }

    public void setCreatorUuid(String creatorUuid) {
        this.creatorUuid = creatorUuid;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationName='" + applicationName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", status=" + status +
                '}';
    }

    public String creatorName() {
        return userRepository.findByGuid(this.creatorUuid).getUsername();
    }
}