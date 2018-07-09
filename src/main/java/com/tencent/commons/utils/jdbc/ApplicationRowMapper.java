/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.utils.jdbc;

import com.tencent.iot.domain.Application;
import com.tencent.iot.domain.ApplicationStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bobzbfeng
 */
public class ApplicationRowMapper implements RowMapper<Application>{

    public ApplicationRowMapper(){}

    @Override
    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
        Application application = new Application();
        application.setId(rs.getInt("c_id"));
        application.setUuid(rs.getString("c_guid"));

        application.setArchived(rs.getBoolean("c_archived"));
        application.setCreateTime(rs.getTimestamp("c_create_time"));

        application.setApplicationName(rs.getString("c_application_name"));
        application.setDescription(rs.getString("c_description"));

        application.setCreatorUuid(rs.getString("c_creator_uuid"));
        application.setStatus(ApplicationStatus.valueOf(rs.getString("c_status")));

        application.setClientId(rs.getString("c_client_id"));

        return application;
    }
}