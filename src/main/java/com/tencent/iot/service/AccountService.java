/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service;

import com.tencent.iot.service.dto.ApplicationClientDetailsDto;
import com.tencent.iot.service.dto.ApplicationDtoPaginated;
import com.tencent.iot.service.dto.ApplicationFormDto;
import com.tencent.iot.service.dto.ResultDto;

/**
 * @author bobzbfeng
 */
public interface AccountService {

    void createApplication(ApplicationFormDto formDto);

    ApplicationDtoPaginated loadMyApplicationDtoPaginated(ApplicationDtoPaginated paginated);

    ApplicationClientDetailsDto loadApplicationClientDetailsByAppUuid(String uuid);

    ResultDto updateApplicationTokenTime(ApplicationClientDetailsDto formDto);
}
