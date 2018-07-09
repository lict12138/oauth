/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.dto;

import com.tencent.iot.domain.Application;
import com.tencent.oauth.domain.oauth.OauthClientDetails;
import com.tencent.oauth.infrastructure.oauth.OAuthUtils;

import java.io.Serializable;

/**
 * @author bobzbfeng
 */
public class ApplicationClientDetailsDto implements Serializable{
    private static final long serialVersionUID = -4675060445363256092L;

    private String applicationName;

    private String clientId;

    private String clientSecret;

    /*
    token有效期，单位用小时
     */
    private int validTime;

    public ApplicationClientDetailsDto(OauthClientDetails clientDetails,Application application){
        this.clientId = clientDetails.clientId();
        this.clientSecret = clientDetails.clientSecret();

        this.validTime = clientDetails.realAccessTokenValidity()/ OAuthUtils.DIVISOR_SECONDS;
        this.applicationName = application.getApplicationName();
    }

    public ApplicationClientDetailsDto(){}

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
    }

    @Override
    public String toString() {
        return "ApplicationClientDetailsDto{" +
                "applicationName='" + applicationName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", validTime=" + validTime +
                '}';
    }
}