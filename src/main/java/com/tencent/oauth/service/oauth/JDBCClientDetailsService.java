/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.oauth.service.oauth;

import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.oauth.OauthClientRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author bobzbfeng
 */
public class JDBCClientDetailsService extends JdbcClientDetailsService  implements InitializingBean{

    @Autowired
    private OauthClientRepository clientRepository;

    public JDBCClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        try {
            return clientRepository.findOauthClientDetails(clientId).toClientDetails();
        } catch (EmptyResultDataAccessException var4) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(null == clientRepository){
            clientRepository = BeanProvider.getBean(OauthClientRepository.class);
        }
    }
}