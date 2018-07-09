
package com.tencent.oauth.service.dto;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 2015/12/8
 *
 * @author bobzbfeng
 */
public class RSTokenDto implements Serializable {
    private static final long serialVersionUID = -2692767677419836098L;

    //required
    private String accessToken;

    //optional
    private String clientId;

    //optional
    private String resourceId;


    public RSTokenDto() {
    }


    public boolean available() {
        return StringUtils.isNotEmpty(accessToken);
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "{" +
                "accessToken='" + accessToken + '\'' +
                ", clientId='" + clientId + '\'' +
                ", resourceId='" + resourceId + '\'' +
                '}';
    }
}
