/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.business.crypt;

import com.tencent.api.service.dto.CryptFormDto;
import com.tencent.api.service.dto.CryptResponseDto;

/**
 * @author bobzbfeng
 */
public interface CryptResolver {

    boolean support(int type);

    CryptResponseDto resolve(CryptFormDto formDto);
}