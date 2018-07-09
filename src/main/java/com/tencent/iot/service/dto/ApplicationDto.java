/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.dto;

import com.tencent.commons.domain.AbstractDto;
import com.tencent.iot.domain.Application;
import com.tencent.iot.domain.ApplicationStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bobzbfeng
 */
public class ApplicationDto extends AbstractDto{
    private static final long serialVersionUID = 4482326930616725025L;

    private String applicationName;

    private String description;

    private ApplicationStatus status = ApplicationStatus.SUBMIT;

    private String creatorName;

    public ApplicationDto(Application application){
        super(application);

        this.applicationName = application.getApplicationName();
        this.description = application.getDescription();
        this.status = application.getStatus();
        this.creatorName = application.creatorName();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public static List<ApplicationDto> toDtos(List<Application> applications){
        List<ApplicationDto> dtos = new ArrayList<>();
        for(Application single:applications){
            dtos.add(new ApplicationDto(single));
        }
        return dtos;
    }
}