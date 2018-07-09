/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.domain;

/**
 * @author bobzbfeng
 */
public enum  AuditLogType {

    LOGIN,

    /*硬件操作*/
    SE_REGISTER_ACTION,

    VERIFY_ACTION,
    ENCRYPT_ACTION,
    DECRYPT_ACTION;
}