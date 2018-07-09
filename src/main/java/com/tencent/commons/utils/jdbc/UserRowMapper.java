/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.tencent.commons.utils.jdbc;

import com.tencent.oauth.domain.security.AccountStatus;
import com.tencent.oauth.domain.security.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 2015/11/16
 *
 * @author Shengzhao Li
 */
public class UserRowMapper implements RowMapper<User> {


    public UserRowMapper() {
    }

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("c_id"));
        user.setUuid(rs.getString("c_guid"));

        user.setArchived(rs.getBoolean("c_archived"));
        user.setCreateTime(rs.getTimestamp("c_create_time"));

        user.setEncryptedPassword(rs.getString("c_password"));
        user.setUsername(rs.getString("c_username"));

        user.setAccountStatus(AccountStatus.valueOf(rs.getString("c_account_status")));

        user.setEnabled(rs.getBoolean("c_enabled"));
        user.setSystemAccount(rs.getBoolean("c_system_admin"));

        return user;
    }
}
