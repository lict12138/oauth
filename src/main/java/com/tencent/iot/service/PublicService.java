/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service;

import com.tencent.oauth.service.dto.user.UserFormDto;

/**
 * @author bobzbfeng
 */
public interface PublicService {

    void registerAccount(UserFormDto formDto);

    boolean isExistedUsername(String username);
}