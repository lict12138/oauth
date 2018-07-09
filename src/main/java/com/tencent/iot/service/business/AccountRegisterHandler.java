/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.business;

import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.domain.security.UserRepository;
import com.tencent.oauth.service.dto.user.UserFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bobzbfeng
 */
public class AccountRegisterHandler {

    private static Logger LOG = LoggerFactory.getLogger(AccountRegisterHandler.class);

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private UserFormDto formDto;

    public AccountRegisterHandler(UserFormDto formDto){
        this.formDto = formDto;
    }

    public void register(){
        User user = formDto.createUser();
        userRepository.saveUser(user);
        LOG.debug("A account {} has been Registered");

    }
}