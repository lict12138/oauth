package com.tencent.oauth.service;


import com.tencent.oauth.domain.oauth.OauthClientDetails;
import com.tencent.oauth.service.dto.RSTokenDto;
import com.tencent.oauth.service.dto.UsernameDto;

public interface OauthService {

    OauthClientDetails loadOauthClientDetails(String clientId);

    UsernameDto validateRSTokenDto(RSTokenDto rsTokenDto);
}
