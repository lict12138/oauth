package com.tencent.oauth.domain.oauth;


import java.util.List;

public interface AccessTokenRepository {

    void saveAccessToken(AccessToken accessToken);

    AccessToken findAccessToken(String tokenId);

    void removeAccessToken(String tokenId);

    void saveRefreshToken(RefreshToken refreshToken);

    RefreshToken findRefreshToken(String tokenId);

    void removeRefreshToken(String tokenId);

    void removeAccessTokenByRefreshToken(String refreshToken);

    AccessToken findAccessTokenByRefreshToken(String refreshToken);

    AccessToken findAccessTokenByAuthenticationId(String authenticationId);

    List<AccessToken> findAccessTokenByUsername(String username);

    List<AccessToken> findAccessTokenByClientId(String clientId);

    List<AccessToken> findAccessTokenByClientIdAndUsername(String clientId, String username);

}
