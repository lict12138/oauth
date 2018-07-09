package com.tencent.oauth.infrastructure.httpclient;


import com.tencent.commons.utils.httpclient.IDSHttpResponse;
import com.tencent.oauth.service.dto.AccessTokenDto;

/**
 * 15-5-18
 *
 * @author bobzbfeng
 */
public class AccessTokenResponseHandler extends AbstractResponseHandler<AccessTokenDto> {


    private AccessTokenDto accessTokenDto;

    public AccessTokenResponseHandler() {
    }

    @Override
    public AccessTokenDto handleResponse(IDSHttpResponse response) {
        if (response.isResponse200()) {
            this.accessTokenDto = responseToDto(response, new AccessTokenDto());
        } else {
            this.accessTokenDto = responseToErrorDto(response, new AccessTokenDto());
        }
        return accessTokenDto;
    }


    public AccessTokenDto getAccessTokenDto() {
        return accessTokenDto;
    }
}
