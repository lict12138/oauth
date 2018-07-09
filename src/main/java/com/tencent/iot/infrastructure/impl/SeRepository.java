/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.infrastructure.impl;

import com.tencent.iot.domain.SE;

/**
 * @author bobzbfeng
 */
public interface SeRepository {
    SE findSEByHid(String hid);

    void saveSE(SE se);

    void updateSEDownCounterByHid(String hid, int downCounter);

    void updateSE(SE newSe);
}