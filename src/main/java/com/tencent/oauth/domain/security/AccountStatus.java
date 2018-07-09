/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.oauth.domain.security;

/**
 * @author bobzbfeng
 * 账号注册状态，对应管理员审核
 */
public enum  AccountStatus {

    SUBMIT("待审核"),
    /*
    该 状态，指不需要审核的账户
     */
    NONE("无"),
    APPROVE("审核通过"),
    REJECT("审核拒绝");

    private String label;

    AccountStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return name();
    }

    public boolean isReject(){
        return this.equals(REJECT);
    }

    public boolean isApprove(){
        return this.equals(APPROVE);
    }
}
