/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.oauth.infrastructure.jdbc;


import com.tencent.commons.utils.jdbc.OauthClientDetailsRowMapper;
import com.tencent.oauth.domain.oauth.OauthClientDetails;
import com.tencent.oauth.domain.oauth.OauthClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bobzbfeng
 */
@Repository("oauthClientRepositoryJDBC")
public class OauthClientRepositoryJDBC implements OauthClientRepository{

    private static OauthClientDetailsRowMapper oauthClientDetailsRowMapper = new OauthClientDetailsRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public OauthClientDetails findOauthClientDetails(String clientId) {
        final String sql = " select * from t_oauth_client_details where  c_client_id = ? ";
        final List<OauthClientDetails> list = this.jdbcTemplate.query(sql, new Object[]{clientId}, oauthClientDetailsRowMapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<OauthClientDetails> findAllOauthClientDetails() {
        final String sql = " select * from t_oauth_client_details ";
        return this.jdbcTemplate.query(sql, oauthClientDetailsRowMapper);
    }

    @Override
    public void saveOauthClientDetails(OauthClientDetails clientDetails) {
        final String sql = " insert into t_oauth_client_details(c_client_id,c_resource_ids,c_client_secret,c_scope,c_authorized_grant_types,c_web_server_redirect_uri," +
                " c_authorities,c_access_token_validity,c_refresh_token_validity,c_additional_information,c_trusted,c_autoapprove) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        this.jdbcTemplate.update(sql, ps -> {
            ps.setString(1, clientDetails.clientId());
            ps.setString(2, clientDetails.resourceIds());

            ps.setString(3, clientDetails.clientSecret());
            ps.setString(4, clientDetails.scope());

            ps.setString(5, clientDetails.authorizedGrantTypes());
            ps.setString(6, clientDetails.webServerRedirectUri());

            ps.setString(7, clientDetails.authorities());
            ps.setObject(8, clientDetails.accessTokenValidity());

            ps.setObject(9, clientDetails.refreshTokenValidity());
            ps.setString(10, clientDetails.additionalInformation());

            ps.setBoolean(11, clientDetails.trusted());
            ps.setString(12, clientDetails.autoApprove());

        });
    }

    @Override
    public boolean removeOauthClientDetails(OauthClientDetails clientDetails) {
        final String sql = " update t_oauth_client_details set c_archived = ? where c_client_id = ? ";
        this.jdbcTemplate.update(sql, clientDetails.archived(), clientDetails.clientId());
        return clientDetails.archived();
    }

    @Override
    public String findResourceIdsByClientId(String clientId) {
        final String sql = " select * from t_oauth_client_details where  c_client_id = ? ";
        final List<OauthClientDetails> list = this.jdbcTemplate.query(sql, new Object[]{clientId}, oauthClientDetailsRowMapper);
        return list.isEmpty() ? null : list.get(0).resourceIds();
    }

    @Override
    public void updateTokenTime(OauthClientDetails clientDetails) {
        final String sql = " update t_oauth_client_details set c_access_token_validity = ? where c_client_id = ? ";
        this.jdbcTemplate.update(sql, clientDetails.accessTokenValidity(), clientDetails.clientId());
    }
}