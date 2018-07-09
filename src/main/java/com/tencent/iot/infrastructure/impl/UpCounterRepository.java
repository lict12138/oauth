/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.infrastructure.impl;

import com.tencent.iot.domain.UpCounter;

/**
 * @author bobzbfeng
 */
public interface UpCounterRepository {
    UpCounter findUpCounterByHidAndCounter(String hid, String counter);

    void saveUpCounter(String hid, String counter);
}