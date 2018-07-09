/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.impl;

import com.tencent.api.service.ApplicationService;
import com.tencent.api.service.business.crypt.CryptHandler;
import com.tencent.api.service.dto.CryptFormDto;
import com.tencent.api.service.dto.CryptResponseDto;
import org.springframework.stereotype.Service;

/**
 * @author bobzbfeng
 */
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService{

    @Override
    public CryptResponseDto cryptAction(CryptFormDto formDto) {
        CryptHandler cryptHandler = new CryptHandler(formDto);
        return cryptHandler.handle();
    }
}