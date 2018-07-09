package com.tencent.oauth.domain.oauth;


import com.tencent.commons.utils.DateUtils;
import com.tencent.commons.utils.UUIDGenerator;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.io.Serializable;
import java.util.Date;


/**
 * @author bobzbfeng
 */


public class AccessToken implements Serializable {

    private static final long serialVersionUID = -3340741887488856243L;

    private String tokenId = UUIDGenerator.generate();

    private Date createTime = DateUtils.now();

    private byte[] token;

    private String authenticationId;

    private byte[] authentication;

    private String username;

    private String clientId;

    private String refreshToken;


    public AccessToken() {
    }

    public String tokenId() {
        return this.tokenId;
    }

    public String createTime() {
        return DateUtils.toDateTime(createTime);
    }

    public String username() {
        return username;
    }

    public String clientId() {
        return clientId;
    }

    public String refreshToken() {
        return refreshToken;
    }

    public OAuth2AccessToken token() {
        return SerializationUtils.deserialize(token);
    }

    public AccessToken token(OAuth2AccessToken token) {
        this.token = SerializationUtils.serialize(token);
        return this;
    }

    public OAuth2Authentication authentication() {
        return SerializationUtils.deserialize(authentication);
    }

    public AccessToken authentication(OAuth2Authentication authentication) {
        this.authentication = SerializationUtils.serialize(authentication);
        return this;
    }

    public AccessToken tokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }

    public AccessToken authenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
        return this;
    }

    public String authenticationId() {
        return this.authenticationId;
    }

    public AccessToken username(String username) {
        this.username = username;
        return this;
    }

    public AccessToken clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public AccessToken refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Date getRealCreateTime(){
        return createTime;
    }

    public byte[] getToken() {
        return token;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "{" +
                "tokenId='" + tokenId + '\'' +
                ", createTime=" + createTime +
                ", authenticationId='" + authenticationId + '\'' +
                ", username='" + username + '\'' +
                ", clientId='" + clientId + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

    public byte[] getAuthentication() {
        return authentication;
    }
}
