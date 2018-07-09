/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.business.system;

import com.tencent.commons.utils.paginated.PaginatedLoader;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.domain.Application;
import com.tencent.iot.infrastructure.impl.ApplicationRepository;
import com.tencent.iot.service.dto.ApplicationDto;
import com.tencent.iot.service.dto.ApplicationDtoPaginated;

import java.util.List;
import java.util.Map;

/**
 * @author bobzbfeng
 */
public class ApplicationPaginatedLoader {

    private transient ApplicationRepository applicationRepository = BeanProvider.getBean(ApplicationRepository.class);

    private ApplicationDtoPaginated paginated;

    public ApplicationPaginatedLoader(ApplicationDtoPaginated paginated){
        this.paginated = paginated;
    }

    public ApplicationDtoPaginated load(){
        Map<String, Object> queryMap = paginated.queryMap();

        return paginated.load(new PaginatedLoader<ApplicationDto>() {
            @Override
            public List<ApplicationDto> loadList() {
                List<Application> applications = applicationRepository.findApplicationList(queryMap);
                return ApplicationDto.toDtos(applications);
            }

            @Override
            public long loadTotalSize() {
                return applicationRepository.totalApplicationList(queryMap);
            }
        });

    }
}