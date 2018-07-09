/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.infrastructure.impl;

import com.tencent.iot.domain.Application;

import java.util.List;
import java.util.Map;

/**
 * @author bobzbfeng
 */
public interface ApplicationRepository {

    void saveApplication(Application application);

    List<Application> findMyApplicationList(Map<String, Object> queryMap);

    long totalMyApplicationList(Map<String, Object> queryMap);

    List<Application> findApplicationList(Map<String, Object> queryMap);

    long totalApplicationList(Map<String, Object> queryMap);

    Application findApplicationByUuid(String uuid);

    void updateApplicationStatus(Application application);

    Application findApplicationByClientId(String clientId);
}