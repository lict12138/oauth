/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.infrastructure.impl;

import com.tencent.commons.utils.jdbc.AbstractJDBCSupport;
import com.tencent.iot.domain.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author bobzbfeng
 */
@Repository("auditLogRepository")
public class AuditLogRepositoryJDBC extends AbstractJDBCSupport implements AuditLogRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveAuditLog(AuditLog auditLog) {
        String sql = "insert into t_audit_log (c_guid,c_create_time,c_archived,c_creator,c_creator_uuid,c_ip,c_content,c_type,c_application_uuid,c_hid)" +
                                                    " values (?,?,?,?,?,?,?,?,?,?)";

        this.jdbcTemplate.update(sql, ps -> {
            ps.setString(1, auditLog.getUuid());
            ps.setTimestamp(2, new Timestamp(auditLog.getRealCreateTime().getTime()));
            ps.setBoolean(3,auditLog.isArchived());
            ps.setString(4, auditLog.getCreator());
            ps.setString(5, auditLog.getCreatorUuid());
            ps.setString(6, auditLog.getIp());
            ps.setString(7, auditLog.getContent());
            ps.setString(8, auditLog.getType().name());
            ps.setString(9, auditLog.getApplicationUuid());
            ps.setString(10, auditLog.getHid());
        });
    }
}