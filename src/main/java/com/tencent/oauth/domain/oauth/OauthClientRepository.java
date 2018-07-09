package com.tencent.oauth.domain.oauth;


import java.util.List;

public interface OauthClientRepository {
    OauthClientDetails findOauthClientDetails(String clientId);

    List<OauthClientDetails> findAllOauthClientDetails();

    void saveOauthClientDetails(OauthClientDetails clientDetails);

    boolean removeOauthClientDetails(OauthClientDetails clientDetails);

    String findResourceIdsByClientId(String clientId);

    void updateTokenTime(OauthClientDetails oauthClientDetails);
}
