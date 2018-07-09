/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.oauth.infrastructure.jdbc;

import com.tencent.commons.utils.jdbc.AbstractJDBCSupport;
import com.tencent.commons.utils.jdbc.UserRowMapper;
import com.tencent.oauth.domain.security.Privilege;
import com.tencent.oauth.domain.security.SecurityUtils;
import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.domain.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bobzbfeng
 */
@Repository("userRepositoryJDBC")
public class UserRepositoryJDBC extends AbstractJDBCSupport implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserRowMapper userRowMapper = new UserRowMapper();

    @Override
    public void saveUser(User user) {
        final String sql = " insert into t_user(c_guid,c_archived,c_create_time,c_password,c_username,c_system_admin,c_enabled,c_account_status) " +
                " values (?,?,?,?,?,?,?,?) ";
        this.jdbcTemplate.update(sql, ps -> {
            ps.setString(1, user.getUuid());
            ps.setBoolean(2, user.isArchived());

            ps.setTimestamp(3, new Timestamp(user.getRealCreateTime().getTime()));

            ps.setString(4, user.password());
            ps.setString(5, user.username());
            ps.setBoolean(6, user.isSystemAccount());
            ps.setBoolean(7, user.isEnabled());
            ps.setString(8, user.getAccountStatus().name());
        });

        //get user id
        final Integer id = this.jdbcTemplate.queryForObject("select c_id from t_user where c_guid = ?", new Object[]{user.getUuid()}, Integer.class);

        //insert privileges
        for (final Privilege privilege : user.getPrivileges()) {
            this.jdbcTemplate.update("insert into t_user_privilege(c_user_id, c_privilege) values (?,?)", ps -> {
                ps.setInt(1, id);
                ps.setString(2, privilege.name());
            });
        }
    }

    @Override
    public User findByUsername(String username) {
        final String sql = " select * from t_user where c_username = ? and c_archived = 0 ";
        final List<User> list = this.jdbcTemplate.query(sql, new Object[]{username}, userRowMapper);

        User user = null;
        if (!list.isEmpty()) {
            user = list.get(0);
            user.getPrivileges().addAll(findPrivileges(user.getId()));
        }

        return user;
    }

    private Collection<? extends Privilege> findPrivileges(int userId) {
        final String sql = " select c_privilege from t_user_privilege where c_user_id = ? ";
        final List<String> strings = this.jdbcTemplate.queryForList(sql, new Object[]{userId}, String.class);

        List<Privilege> privileges = new ArrayList<>(strings.size());
        privileges.addAll(strings.stream().map(Privilege::valueOf).collect(Collectors.toList()));
        return privileges;
    }


    @Override
    public void removeUser(User user) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public User findByGuid(String guid) {
        final String sql = " select * from t_user where  c_guid = ? ";
        final List<User> list = this.jdbcTemplate.query(sql, new Object[]{guid}, userRowMapper);

        User user = null;
        if (!list.isEmpty()) {
            user = list.get(0);
            user.getPrivileges().addAll(findPrivileges(user.getId()));
        }

        return user;
    }

    @Override
    public User currentUser() {
        return findByGuid(SecurityUtils.currentUserGuid());
    }

    @Override
    public List<User> findUserPaginated(Map<String, Object> map) {
        String sql = " select * from t_user ";
        sql = addPagination(sql,map);
        return  this.jdbcTemplate.query(sql, new Object[]{}, userRowMapper);
    }

    @Override
    public long totalUserPaginated(Map<String, Object> map) {
        final String sql = " select count(*) from t_user ";
        addPagination(sql,map);
        return  this.jdbcTemplate.queryForInt(sql);
    }

    @Override
    public long countUsers() {
        String sql = "select count(*) from t_user";
        return this.jdbcTemplate.queryForInt(sql);
    }

    @Override
    public void updateUserStatus(User user) {
        String sql = "update t_user set c_account_status = ? , c_enabled = ? where c_id = ?";
        this.jdbcTemplate.update(sql,ps -> {
            ps.setString(1, user.getAccountStatus().name());
            ps.setBoolean(2, user.isEnabled());
            ps.setInt(3, user.getId());
        });
    }

}