
package com.tencent.oauth.service.oauth.validate;



import com.tencent.oauth.domain.oauth.AccessToken;
import com.tencent.oauth.service.dto.RSTokenDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/12/8
 *
 * @author bobzbfeng
 */
public class RSClientIdValidator implements RSAuthValidator {

    private static final Logger LOG = LoggerFactory.getLogger(RSClientIdValidator.class);


    @Override
    public boolean support(RSTokenDto tokenDto) {
        return StringUtils.isNotEmpty(tokenDto.getClientId());
    }

    @Override
    public boolean validate(RSTokenDto tokenDto, AccessToken accessToken) {

        final String clientId = tokenDto.getClientId();

        final String accessTokenClientId = accessToken.clientId();
        if (!accessTokenClientId.equals(clientId)) {
            LOG.debug("Valid failed, because invalid clientId: {}", clientId);
            return false;
        }

        return true;
    }
}
