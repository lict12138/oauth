/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.impl;

import com.tencent.api.service.RegisterService;
import com.tencent.api.service.business.register.SERegister;
import com.tencent.api.service.dto.RegisterFormDto;
import com.tencent.api.service.dto.RegisterResponseDto;
import org.springframework.stereotype.Service;

/**
 * @author bobzbfeng
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService{
    @Override
    public RegisterResponseDto register(RegisterFormDto formDto) {
        SERegister register = new SERegister(formDto);
        return register.register();
    }
}