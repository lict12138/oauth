/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.impl;

import com.tencent.iot.service.PublicService;
import com.tencent.iot.service.business.AccountRegisterHandler;
import com.tencent.oauth.domain.security.UserRepository;
import com.tencent.oauth.service.dto.user.UserFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bobzbfeng
 */
@Service("publicService")
public class PublicServiceImpl implements PublicService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerAccount(UserFormDto formDto) {
        AccountRegisterHandler accountRegisterHandler = new AccountRegisterHandler(formDto);
        accountRegisterHandler.register();
    }

    @Override
    public boolean isExistedUsername(String username) {
        return null != userRepository.findByUsername(username);
    }
}