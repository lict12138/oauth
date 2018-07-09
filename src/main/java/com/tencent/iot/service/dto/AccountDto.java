/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.dto;

import com.tencent.commons.domain.AbstractDto;
import com.tencent.oauth.domain.security.AccountStatus;
import com.tencent.oauth.domain.security.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bobzbfeng
 */
public class AccountDto extends AbstractDto {

    private static final long serialVersionUID = 4815046987445970970L;
    private String username;

    private AccountStatus accountStatus;

    public AccountDto(User user){
        super(user);

        this.username = user.getUsername();
        this.accountStatus = user.getAccountStatus();
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

    public static List<AccountDto> toDtos(List<User> users){
        List<AccountDto> accountDtos = new ArrayList<>();
        for(User user:users){
            accountDtos.add(new AccountDto(user));
        }
        return accountDtos;
    }
}