/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.dto;

import com.tencent.commons.utils.paginated.DefaultPaginated;

import java.util.Map;

/**
 * @author bobzbfeng
 */
public class ApplicationDtoPaginated extends DefaultPaginated<ApplicationDto>{

    private String applicationName;

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> queryMap = super.queryMap();
        queryMap.put("applicationName",applicationName);
        return queryMap;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}