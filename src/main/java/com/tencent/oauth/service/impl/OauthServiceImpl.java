package com.tencent.oauth.service.impl;


import com.tencent.oauth.domain.oauth.OauthClientRepository;
import com.tencent.oauth.domain.oauth.OauthClientDetails;
import com.tencent.oauth.service.OauthService;
import com.tencent.oauth.service.dto.RSTokenDto;
import com.tencent.oauth.service.dto.UsernameDto;
import com.tencent.oauth.service.oauth.validate.RSTokenDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("oauthService")
public class OauthServiceImpl implements OauthService {
    @Autowired
    private OauthClientRepository oauthClientRepository;


    @Override
    public OauthClientDetails loadOauthClientDetails(String clientId) {
        return oauthClientRepository.findOauthClientDetails(clientId);
    }

    @Override
    public UsernameDto validateRSTokenDto(RSTokenDto rsTokenDto) {
        RSTokenDtoValidator rsTokenDtoValidator = new RSTokenDtoValidator(rsTokenDto);
        return rsTokenDtoValidator.validate();
    }


}
