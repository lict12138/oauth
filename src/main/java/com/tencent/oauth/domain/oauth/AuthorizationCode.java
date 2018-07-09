package com.tencent.oauth.domain.oauth;


import com.tencent.commons.utils.DateUtils;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bobzbfeng
 */
public class AuthorizationCode implements Serializable {


    private static final long serialVersionUID = -4991009777728508178L;

    private String code;

    private Date createTime = DateUtils.now();

    private Long version;


    private byte[] authenticationBytes;

    public AuthorizationCode() {
    }

    public String code() {
        return code;
    }

    public Date createTime() {
        return createTime;
    }


    public AuthorizationCode code(String code) {
        this.code = code;
        return this;
    }

    public OAuth2Authentication authentication() {
        return SerializationUtils.deserialize(authenticationBytes);
    }

    public AuthorizationCode authentication(OAuth2Authentication authentication) {
        this.authenticationBytes = SerializationUtils.serialize(authentication);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", createTime=" + createTime +
                ", version=" + version +
                '}';
    }
}
