/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.dto;

import java.io.Serializable;

/**
 * @author bobzbfeng
 */
public class ResultDto implements Serializable{
    private static final long serialVersionUID = 7071202960775509914L;

    private boolean success = false;

    private String errorMessage;

    public ResultDto() {
    }

    public ResultDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ResultDto(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}