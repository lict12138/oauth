/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.dto;

import java.io.Serializable;
import static com.tencent.commons.web.WebUtils.*;

/**
 * @author bobzbfeng
 */
public class ApplicationFormDto implements Serializable{
    private static final long serialVersionUID = -5122591248135023672L;

    private String applicationName;

    private String description;

    public ApplicationFormDto() {
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = paramFilter(applicationName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = paramFilter(description);
    }
}