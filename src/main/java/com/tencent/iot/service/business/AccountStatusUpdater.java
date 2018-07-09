/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.business;

import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.security.AccountStatus;
import com.tencent.oauth.domain.security.SecurityUtils;
import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.domain.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bobzbfeng
 */
public class AccountStatusUpdater {

    private static Logger LOG = LoggerFactory.getLogger(AccountStatusUpdater.class);

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private String uuid;

    private AccountStatus accountStatus;

    public AccountStatusUpdater(String uuid,AccountStatus accountStatus){
        this.uuid = uuid;
        this.accountStatus = accountStatus;
    }

    public void update(){
        User user = userRepository.findByGuid(uuid);
        if(null == user || user.isSystemAccount() || user.getAccountStatus().equals(accountStatus)){
            return;
        }

        user.setAccountStatus(accountStatus);
        /*
        状态为审核通过的话，enable 设为true,代表账号可用
         */
        if(accountStatus.isApprove()){
            user.setEnabled(true);
        }
        userRepository.updateUserStatus(user);
        LOG.debug("{} has updated Account {} status to {}", SecurityUtils.currUser().getUsername(),user.username(),accountStatus);
    }
}