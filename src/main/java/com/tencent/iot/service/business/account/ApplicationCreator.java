/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.business.account;

import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.domain.Application;
import com.tencent.iot.infrastructure.impl.ApplicationRepository;
import com.tencent.iot.service.dto.ApplicationFormDto;
import com.tencent.oauth.domain.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bobzbfeng
 */
public class ApplicationCreator {

    private static Logger LOG = LoggerFactory.getLogger(ApplicationCreator.class);

    private transient ApplicationRepository applicationRepository = BeanProvider.getBean(ApplicationRepository.class);

    private ApplicationFormDto formDto;

    public ApplicationCreator(ApplicationFormDto formDto){
        this.formDto = formDto;
    }

    public void create(){
        Application application = createByForm();
        applicationRepository.saveApplication(application);
        LOG.debug("{} has created a Application {}",SecurityUtils.currUser().username(),application);
    }

    private Application createByForm() {
        Application application = new Application();
        application.setApplicationName(formDto.getApplicationName());
        application.setDescription(formDto.getDescription());

        application.setCreatorUuid(SecurityUtils.currentUserGuid());
        return application;
    }
}