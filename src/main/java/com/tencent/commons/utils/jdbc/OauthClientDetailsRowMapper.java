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

import com.tencent.oauth.domain.oauth.OauthClientDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 2015/11/16
 *
 * @author Shengzhao Li
 */
public class OauthClientDetailsRowMapper implements RowMapper<OauthClientDetails> {


    public OauthClientDetailsRowMapper() {
    }

    @Override
    public OauthClientDetails mapRow(ResultSet rs, int i) throws SQLException {
        OauthClientDetails clientDetails = new OauthClientDetails();

        clientDetails.clientId(rs.getString("c_client_id"));
        clientDetails.resourceIds(rs.getString("c_resource_ids"));
        clientDetails.clientSecret(rs.getString("c_client_secret"));

        clientDetails.scope(rs.getString("c_scope"));
        clientDetails.authorizedGrantTypes(rs.getString("c_authorized_grant_types"));
        clientDetails.webServerRedirectUri(rs.getString("c_web_server_redirect_uri"));

        clientDetails.authorities(rs.getString("c_authorities"));
        clientDetails.accessTokenValidity(getInteger(rs, "c_access_token_validity"));
        clientDetails.refreshTokenValidity(getInteger(rs, "c_refresh_token_validity"));

        clientDetails.additionalInformation(rs.getString("c_additional_information"));
        clientDetails.createTime(rs.getTimestamp("c_create_time"));
        clientDetails.archived(rs.getBoolean("c_archived"));

        clientDetails.trusted(rs.getBoolean("c_trusted"));
        clientDetails.autoApprove(rs.getString("c_autoapprove"));

        return clientDetails;
    }


    private Integer getInteger(ResultSet rs, String columnName) throws SQLException {
        final Object object = rs.getObject(columnName);
        if (object != null) {
            return (Integer) object;
        }
        return null;
    }

}
