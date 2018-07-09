/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.dto;

import java.io.Serializable;

/**
 * @author bobzbfeng
 */
public class RegisterResponseDto implements Serializable{
    private static final long serialVersionUID = -8246672840865802431L;

    Integer	err = -1;
    String	encryptedKey;
    String	downCounter;


    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err = err;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public String getDownCounter() {
        return downCounter;
    }

    public void setDownCounter(String downCounter) {
        this.downCounter = downCounter;
    }
}