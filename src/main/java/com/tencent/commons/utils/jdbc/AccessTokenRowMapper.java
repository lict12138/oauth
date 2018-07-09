/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.utils.jdbc;

import com.tencent.oauth.domain.oauth.AccessToken;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bobzbfeng
 */
public class AccessTokenRowMapper implements RowMapper<AccessToken>{
    @Override
    public AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccessToken accessToken = new AccessToken();
        accessToken.tokenId(rs.getString("c_token_id"));
        accessToken.setCreateTime(rs.getTimestamp("c_create_time"));
        accessToken.setToken(rs.getBytes("c_token"));
        accessToken.authenticationId(rs.getString("c_authentication_id"));
        accessToken.setAuthentication(rs.getBytes("c_authentication"));
        accessToken.username(rs.getString("c_user_name"));
        accessToken.clientId(rs.getString("c_client_id"));
        accessToken.refreshToken(rs.getString("c_refresh_token"));
        return accessToken;
    }
}