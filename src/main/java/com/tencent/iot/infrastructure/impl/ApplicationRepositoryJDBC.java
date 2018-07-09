/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.infrastructure.impl;

import com.tencent.commons.utils.jdbc.AbstractJDBCSupport;
import com.tencent.iot.domain.Application;
import com.tencent.commons.utils.jdbc.ApplicationRowMapper;
import com.tencent.oauth.domain.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author bobzbfeng
 */
@Repository("applicationRepository")
public class ApplicationRepositoryJDBC extends AbstractJDBCSupport implements ApplicationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ApplicationRowMapper rowMapper = new ApplicationRowMapper();

    @Override
    public void saveApplication(Application application) {
        String sql = " insert into t_application(c_guid, c_application_name, c_description, c_status, c_creator_uuid, c_create_time,c_archived,c_client_id)" +
                "        values (?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, ps -> {
            ps.setString(1, application.getUuid());
            ps.setString(2, application.getApplicationName());
            ps.setString(3, application.getDescription());
            ps.setString(4, application.getStatus().name());
            ps.setString(5, application.getCreatorUuid());

            ps.setTimestamp(6, new Timestamp(application.getRealCreateTime().getTime()));
            ps.setBoolean(7,application.isArchived());
            ps.setString(8, application.getClientId());
        });
    }

    @Override
    public List<Application> findMyApplicationList(Map<String, Object> queryMap) {
        String sql = "select * from t_application where c_creator_uuid = '"+SecurityUtils.currentUserGuid()+"'";
        sql = addPagination(sql,queryMap);
        return this.jdbcTemplate.query(sql,new Object[]{},rowMapper);
    }

    @Override
    public long totalMyApplicationList(Map<String, Object> queryMap) {
        String sql = "select * from t_application where c_creator_uuid = '"+SecurityUtils.currentUserGuid()+"'";
        List<Application> applications = this.jdbcTemplate.query(sql, new Object[]{}, rowMapper);
        return applications.size();
    }

    @Override
    public List<Application> findApplicationList(Map<String, Object> queryMap) {
        String sql = "select * from t_application ";
        sql = addPagination(sql,queryMap);
        return this.jdbcTemplate.query(sql,new Object[]{},rowMapper);
    }

    @Override
    public long totalApplicationList(Map<String, Object> queryMap) {
        String sql = "select * from t_application ";
        List<Application> applications = this.jdbcTemplate.query(sql, new Object[]{}, rowMapper);
        return applications.size();
    }

    @Override
    public Application findApplicationByUuid(String uuid) {
        String sql = "select * from t_application where c_guid  = ?";
        List<Application> applications = this.jdbcTemplate.query(sql, new Object[]{uuid}, rowMapper);
        if(applications.isEmpty()){
            return null;
        }
        return applications.get(0);
    }

    @Override
    public void updateApplicationStatus(Application application) {
        String sql = "update t_application set c_status = ? , c_client_id = ? where c_guid = ?";
        this.jdbcTemplate.update(sql,ps -> {
            ps.setString(1, application.getStatus().name());
            ps.setString(2, application.getClientId());
            ps.setString(3, application.getUuid());
        });
    }

    @Override
    public Application findApplicationByClientId(String clientId) {
        String sql = "select * from t_application where c_client_id  = ?";
        List<Application> applications = this.jdbcTemplate.query(sql, new Object[]{clientId}, rowMapper);
        if(applications.isEmpty()){
            return null;
        }
        return applications.get(0);
    }
}