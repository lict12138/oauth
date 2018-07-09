
package com.tencent.oauth.service.oauth.validate;



import com.tencent.oauth.domain.oauth.AccessToken;
import com.tencent.oauth.service.dto.RSTokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * 2015/12/8
 *
 * @author bobzbfeng
 */
public class RSAccessTokenValidator implements RSAuthValidator {

    private static final Logger LOG = LoggerFactory.getLogger(RSAccessTokenValidator.class);

    @Override
    public boolean support(RSTokenDto tokenDto) {
        //always
        return true;
    }

    @Override
    public boolean validate(RSTokenDto tokenDto, AccessToken accessToken) {

        if (accessToken == null) {
            LOG.debug("Validate failed, because not found AccessToken, accessToken: {}", tokenDto.getAccessToken());
            return false;
        }

        final OAuth2AccessToken token = accessToken.token();
        if (token == null) {
            LOG.debug("Validate failed, because OAuth2AccessToken is null, accessToken: {}", tokenDto.getAccessToken());
            return false;
        }

        final boolean expired = token.isExpired();
        if (expired) {
            LOG.debug("Validate failed, because OAuth2AccessToken[{}] expired, tokenId: {}", token, tokenDto.getAccessToken());
        }


        return !expired;
    }
}
