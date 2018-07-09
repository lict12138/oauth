/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.impl;

import com.tencent.iot.domain.Application;
import com.tencent.iot.domain.ApplicationStatus;
import com.tencent.iot.infrastructure.impl.ApplicationRepository;
import com.tencent.iot.service.SystemService;
import com.tencent.iot.service.business.AccountPaginatedLoader;
import com.tencent.iot.service.business.AccountStatusUpdater;
import com.tencent.iot.service.business.system.ApplicationApproveHandler;
import com.tencent.iot.service.business.system.ApplicationPaginatedLoader;
import com.tencent.iot.service.dto.AccountDto;
import com.tencent.iot.service.dto.AccountPaginated;
import com.tencent.iot.service.dto.ApplicationDto;
import com.tencent.iot.service.dto.ApplicationDtoPaginated;
import com.tencent.oauth.domain.security.AccountStatus;
import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.domain.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bobzbfeng
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public AccountPaginated loadAccountPaginated(AccountPaginated paginated) {
        AccountPaginatedLoader loader = new AccountPaginatedLoader(paginated);
        return loader.load();
    }

    @Override
    public AccountDto loadAccountDtoByUuid(String uuid) {
        User user = userRepository.findByGuid(uuid);
        return new AccountDto(user);
    }

    @Override
    public void approveAccountByUuid(String uuid) {
        AccountStatusUpdater accountStatusUpdater = new AccountStatusUpdater(uuid, AccountStatus.APPROVE);
        accountStatusUpdater.update();
    }

    @Override
    public void rejectAccountRegisterByUuid(String uuid) {
        AccountStatusUpdater accountStatusUpdater = new AccountStatusUpdater(uuid, AccountStatus.REJECT);
        accountStatusUpdater.update();
    }

    @Override
    public ApplicationDtoPaginated loadApplicationPaginated(ApplicationDtoPaginated paginated) {
        ApplicationPaginatedLoader loader = new ApplicationPaginatedLoader(paginated);
        return loader.load();
    }

    @Override
    public ApplicationDto loadApplicationDtoByUuid(String uuid) {
        Application application = applicationRepository.findApplicationByUuid(uuid);
        return new ApplicationDto(application);
    }

    @Override
    public void approveApplicationByUuid(String uuid) {
        ApplicationApproveHandler handler = new ApplicationApproveHandler(uuid, ApplicationStatus.APPROVE);
        handler.handle();
    }

    @Override
    public void rejectApplicationRegisterByUuid(String uuid) {
        ApplicationApproveHandler handler = new ApplicationApproveHandler(uuid, ApplicationStatus.REJECT);
        handler.handle();
    }
}