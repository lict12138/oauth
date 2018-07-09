package com.tencent.oauth.domain.oauth;


import com.tencent.commons.utils.DateUtils;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author bobzbfeng
 */
public class RefreshToken implements Serializable {
    private static final long serialVersionUID = 5979990986390801027L;

    private String tokenId;

    private Date createTime = DateUtils.now();

    private byte[] token;

    private byte[] authentication;

    public RefreshToken() {
    }

    public OAuth2RefreshToken token() {
        return SerializationUtils.deserialize(token);
    }

    public RefreshToken token(OAuth2RefreshToken token) {
        this.token = SerializationUtils.serialize(token);
        return this;
    }

    public OAuth2Authentication authentication() {
        return SerializationUtils.deserialize(authentication);
    }

    public RefreshToken authentication(OAuth2Authentication authentication) {
        this.authentication = SerializationUtils.serialize(authentication);
        return this;
    }

    public RefreshToken tokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }

    public String createTime() {
        return DateUtils.toDateTime(createTime);
    }

    public Date getRealCreateTime(){
        return createTime;
    }

    public String tokenId() {
        return this.tokenId;
    }

    public byte[] getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "{" +
                ", createTime=" + createTime +
                ", tokenId='" + tokenId + '\'' +
                '}';
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
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
}
