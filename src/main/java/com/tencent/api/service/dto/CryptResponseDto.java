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
public class CryptResponseDto implements Serializable{
    private static final long serialVersionUID = 3477489555032479769L;

    Integer	err = -1;
    String	data;

    public CryptResponseDto(String errorData){
        this.data = errorData;
    }

    public CryptResponseDto(){
    }

    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err = err;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}