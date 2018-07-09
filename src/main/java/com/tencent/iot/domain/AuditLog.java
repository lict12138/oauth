/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.domain;

import com.tencent.commons.domain.AbstractDomain;
import com.tencent.commons.web.WebUtils;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.infrastructure.impl.AuditLogRepository;

/**
 * 日志审计
 * @author bobzbfeng
 */
public class AuditLog extends AbstractDomain{
    private static final long serialVersionUID = -7532210822329850013L;

    private transient AuditLogRepository logRepository = BeanProvider.getBean(AuditLogRepository.class);

    private String creator;

    private String creatorUuid;

    private String ip;

    private String content;

    private AuditLogType type;


    //---------------------------验签，加密，解密，注册--------------------------
    private String applicationUuid;

    private String hid;

    /*--------------------------保存方法----------------------*/


    public static AuditLog createLoginAuditLog(String creator,String creatorUuid,String content){
        AuditLog log = new AuditLog().setCreator(creator).setCreatorUuid(creatorUuid).setContent(content)
                .setIp(WebUtils.getIp()).setType(AuditLogType.LOGIN);
        return log.save();
    }

    public static AuditLog createSERegisterAuditLog(String hid,String content){
        AuditLog log = new AuditLog().setHid(hid).setContent(content)
                .setIp(WebUtils.getIp()).setType(AuditLogType.SE_REGISTER_ACTION);
        return log.save();
    }

    public static AuditLog createVerifyAuditLog(String hid,String applicationUuid,String content){
        AuditLog log = new AuditLog().setHid(hid).setApplicationUuid(applicationUuid).setContent(content)
                .setIp(WebUtils.getIp()).setType(AuditLogType.VERIFY_ACTION);
        return log.save();
    }

    public static AuditLog createEncryptAuditLog(String hid,String applicationUuid,String content){
        AuditLog log = new AuditLog().setHid(hid).setApplicationUuid(applicationUuid).setContent(content)
                .setIp(WebUtils.getIp()).setType(AuditLogType.ENCRYPT_ACTION);
        return log.save();
    }

    public static AuditLog createDecryptAuditLog(String hid,String applicationUuid,String content){
        AuditLog log = new AuditLog().setHid(hid).setApplicationUuid(applicationUuid).setContent(content)
                .setIp(WebUtils.getIp()).setType(AuditLogType.DECRYPT_ACTION);
        return log.save();
    }

    /**
     * 使用新线程去保存
     *
     * @return AuditLog self
     */
    private AuditLog save() {
        final AuditLog auditLog = this;
        new Thread(() -> logRepository.saveAuditLog(auditLog)).start();
        return this;
    }

    /*-----------------getter  setter----------------------*/

    public String getCreatorUuid() {
        return creatorUuid;
    }

    public AuditLog setCreatorUuid(String creatorUuid) {
        this.creatorUuid = creatorUuid;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public AuditLog setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AuditLog setContent(String content) {
        this.content = content;
        return this;
    }

    public String getApplicationUuid() {
        return applicationUuid;
    }

    public AuditLog setApplicationUuid(String applicationUuid) {
        this.applicationUuid = applicationUuid;
        return this;
    }

    public String getHid() {
        return hid;
    }

    public AuditLog setHid(String hid) {
        this.hid = hid;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public AuditLog setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public AuditLogType getType() {
        return type;
    }

    public AuditLog setType(AuditLogType type) {
        this.type = type;
        return this;
    }
}