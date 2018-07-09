
package com.tencent.oauth.service.oauth.validate;



import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.oauth.OauthClientRepository;
import com.tencent.oauth.domain.oauth.AccessToken;
import com.tencent.oauth.service.dto.RSTokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * 2015/12/8
 *
 * @author bobzbfeng
 */
public class RSResourceIdValidator implements RSAuthValidator {

    private static final Logger LOG = LoggerFactory.getLogger(RSResourceIdValidator.class);

    private transient OauthClientRepository oauthClientRepository = BeanProvider.getBean(OauthClientRepository.class);

    @Override
    public boolean support(RSTokenDto tokenDto) {
        return StringUtils.hasText(tokenDto.getResourceId());
    }

    @Override
    public boolean validate(RSTokenDto tokenDto, AccessToken myAccessToken) {
        final String resourceId = tokenDto.getResourceId();

        final String clientId = myAccessToken.clientId();
        String resourceIds = oauthClientRepository.findResourceIdsByClientId(clientId);

        if (!StringUtils.hasText(resourceIds)) {
            LOG.debug("Valid failed, because resourceIds is not or empty of clientId: {}", clientId);
            return false;
        }

        final Set<String> rsSet = StringUtils.commaDelimitedListToSet(resourceIds);
        if (!rsSet.contains(resourceId)) {
            LOG.debug("Valid failed, because resourceIds[{}] exclude resourceId[{}]", resourceIds, resourceId);
            return false;
        }

        //Return successful
        return true;
    }
}
