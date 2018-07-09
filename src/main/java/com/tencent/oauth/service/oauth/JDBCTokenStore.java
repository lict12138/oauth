/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.oauth.service.oauth;

import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.oauth.AccessToken;
import com.tencent.oauth.domain.oauth.AccessTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author bobzbfeng
 */
public class JDBCTokenStore extends JdbcTokenStore implements InitializingBean{

    private static final Logger LOG = LoggerFactory.getLogger(JdbcTokenStore.class);

    public JDBCTokenStore(DataSource dataSource) {
        super(dataSource);
    }

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        final String authenticationId = authenticationKeyGenerator.extractKey(authentication);
        OAuth2AccessToken accessToken = null;

        try {
            AccessToken token = accessTokenRepository.findAccessTokenByAuthenticationId(authenticationId);
            accessToken = token != null ? token.token() : null;
        } catch (IllegalArgumentException e) {
            LOG.error("Could not extract access token for authentication {}",authentication);
        }

        if (accessToken != null
                && !authenticationId.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
            removeAccessToken(accessToken.getValue());
            // Keep the store consistent (maybe the same user is represented by this authentication but the details have
            // changed)
            storeAccessToken(accessToken, authentication);
        }

        return accessToken;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        LOG.debug("Call storeAccessToken, token = {}, authentication = {}", token, authentication);
        String refreshToken = token.getRefreshToken() != null ? token.getRefreshToken().getValue() : null;

        AccessToken accessToken = new AccessToken()
                .tokenId(extractTokenKey(token.getValue()))
                .token(token)
                .authenticationId(authenticationKeyGenerator.extractKey(authentication))
                .username(authentication.isClientOnly() ? null : authentication.getName())
                .clientId(authentication.getOAuth2Request().getClientId())
                .authentication(authentication)
                .refreshToken(extractTokenKey(refreshToken));

        accessTokenRepository.saveAccessToken(accessToken);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        LOG.debug("Call readAccessToken, tokenValue = {}", tokenValue);
        OAuth2AccessToken token = null;

        try {
            final String tokenId = extractTokenKey(tokenValue);

            final AccessToken accessToken = accessTokenRepository.findAccessToken(tokenId);
            token = accessToken == null ? null : accessToken.token();
        } catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize access token for {}", tokenValue);
            removeAccessToken(tokenValue);
        }

        return token;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        removeAccessToken(token.getValue());
    }


    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        LOG.debug("Call readAuthentication, token = {}", token);
        OAuth2Authentication authentication = null;
        try {
            final String tokenId = extractTokenKey(token);
            AccessToken accessToken = accessTokenRepository.findAccessToken(tokenId);
            authentication = accessToken == null ? null : accessToken.authentication();

        } catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize authentication for {}", token);
            removeAccessToken(token);
        }
        return authentication;
    }

    @Override
    public void removeAccessToken(String tokenValue) {
        final String tokenId = extractTokenKey(tokenValue);
        accessTokenRepository.removeAccessToken(tokenId);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if(null == accessTokenRepository){
            accessTokenRepository  = BeanProvider.getBean(AccessTokenRepository.class);
        }
    }
}