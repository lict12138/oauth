/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.dto;

import com.tencent.commons.utils.paginated.DefaultPaginated;
import com.tencent.oauth.domain.security.AccountStatus;

import java.util.Map;

/**
 * @author bobzbfeng
 */
public class AccountPaginated extends DefaultPaginated<AccountDto> {

    private String username;

    private AccountStatus accountStatus;

    @Override
    public Map<String, Object> queryMap() {
        Map<String,Object> map = super.queryMap();
        map.put("username",username);
        map.put("accountStatus",accountStatus);
        return map;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}