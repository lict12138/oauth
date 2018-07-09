/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.domain;

/**
 * @author bobzbfeng
 */
public enum  ApplicationStatus {

    SUBMIT("待审核"),
    APPROVE("审核通过"),
    REJECT("审核拒绝");

    private String label;

    ApplicationStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue(){
        return name();
    }
}