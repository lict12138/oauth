/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.impl;

import com.tencent.iot.service.AccountService;
import com.tencent.iot.service.business.account.ApplicationClientDetailsLoader;
import com.tencent.iot.service.business.account.ApplicationCreator;
import com.tencent.iot.service.business.account.MyApplicationDtoPaginatedLoader;
import com.tencent.iot.service.business.account.TokenValidTimeUpdater;
import com.tencent.iot.service.dto.ApplicationClientDetailsDto;
import com.tencent.iot.service.dto.ApplicationDtoPaginated;
import com.tencent.iot.service.dto.ApplicationFormDto;
import com.tencent.iot.service.dto.ResultDto;
import org.springframework.stereotype.Service;

/**
 * @author bobzbfeng
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService{


    @Override
    public void createApplication(ApplicationFormDto formDto) {
        ApplicationCreator applicationCreator = new ApplicationCreator(formDto);
        applicationCreator.create();
    }

    @Override
    public ApplicationDtoPaginated loadMyApplicationDtoPaginated(ApplicationDtoPaginated paginated) {
        MyApplicationDtoPaginatedLoader loader = new MyApplicationDtoPaginatedLoader(paginated);
        return loader.load();
    }

    @Override
    public ApplicationClientDetailsDto loadApplicationClientDetailsByAppUuid(String uuid) {
        ApplicationClientDetailsLoader loader = new ApplicationClientDetailsLoader(uuid);
        return loader.load();
    }

    @Override
    public ResultDto updateApplicationTokenTime(ApplicationClientDetailsDto formDto) {
        TokenValidTimeUpdater updater = new TokenValidTimeUpdater(formDto);
        return updater.update();
    }
}