/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service;

import com.tencent.api.service.dto.RegisterFormDto;
import com.tencent.api.service.dto.RegisterResponseDto;

/**
 * @author bobzbfeng
 */
public interface RegisterService {
    RegisterResponseDto register(RegisterFormDto formDto);
}
