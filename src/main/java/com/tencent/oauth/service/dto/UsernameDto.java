
package com.tencent.oauth.service.dto;

import java.io.Serializable;

/**
 * 2015/12/30
 *
 * @author bobzbfeng
 */
public class UsernameDto implements Serializable {
    private static final long serialVersionUID = 3679410815929666974L;

    private String username;

    private int errorCode = 0;

    public UsernameDto() {
    }

    public UsernameDto(int errorCode) {
        this.errorCode = errorCode;
    }

    public UsernameDto(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
