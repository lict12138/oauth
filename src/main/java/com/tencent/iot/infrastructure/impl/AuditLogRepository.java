/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.infrastructure.impl;

import com.tencent.iot.domain.AuditLog;

/**
 * @author bobzbfeng
 */
public interface AuditLogRepository {

    void saveAuditLog(AuditLog auditLog);
}