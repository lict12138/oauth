/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service;

import com.tencent.iot.service.dto.AccountDto;
import com.tencent.iot.service.dto.AccountPaginated;
import com.tencent.iot.service.dto.ApplicationDto;
import com.tencent.iot.service.dto.ApplicationDtoPaginated;

/**
 * @author bobzbfeng
 */
public interface SystemService {
    AccountPaginated loadAccountPaginated(AccountPaginated paginated);

    AccountDto loadAccountDtoByUuid(String uuid);

    void approveAccountByUuid(String uuid);

    void rejectAccountRegisterByUuid(String uuid);

    ApplicationDtoPaginated loadApplicationPaginated(ApplicationDtoPaginated paginated);

    ApplicationDto loadApplicationDtoByUuid(String uuid);

    void approveApplicationByUuid(String uuid);

    void rejectApplicationRegisterByUuid(String uuid);
}
