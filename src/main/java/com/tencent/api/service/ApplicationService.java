/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service;

import com.tencent.api.service.dto.CryptFormDto;
import com.tencent.api.service.dto.CryptResponseDto;

/**
 * @author bobzbfeng
 */
public interface ApplicationService {
    CryptResponseDto cryptAction(CryptFormDto formDto);
}
