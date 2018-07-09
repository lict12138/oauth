
package com.tencent.oauth.service.oauth.validate;



import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.oauth.AccessTokenRepository;
import com.tencent.oauth.domain.oauth.AccessToken;
import com.tencent.oauth.service.dto.RSTokenDto;
import com.tencent.oauth.service.dto.UsernameDto;
import com.tencent.oauth.service.oauth.ExtractTokenKeyDigester;
import com.tencent.oauth.service.oauth.MD5ExtractTokenKeyDigester;
import com.tencent.oauth.infrastructure.oauth.ErrorNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 2015/12/8
 *
 * @author bobzbfeng
 */
public class RSTokenDtoValidator {

    private static final Logger LOG = LoggerFactory.getLogger(RSTokenDtoValidator.class);

    private transient AccessTokenRepository accessTokenRepository = BeanProvider.getBean(AccessTokenRepository.class);
    private ExtractTokenKeyDigester tokenKeyDigester = new MD5ExtractTokenKeyDigester();


    private List<RSAuthValidator> authValidators = new ArrayList<>();

    private RSTokenDto rsTokenDto;

    public RSTokenDtoValidator(RSTokenDto rsTokenDto) {
        this.rsTokenDto = rsTokenDto;
        initialValidators();
    }

    private void initialValidators() {
        this.authValidators.add(new RSAccessTokenValidator());
        this.authValidators.add(new RSClientIdValidator());
        this.authValidators.add(new RSResourceIdValidator());
    }

    public UsernameDto validate() {
        LOG.debug("Start validate RSTokenDto: {}", rsTokenDto);

        //Load AccessToken from Repository
        final String tokenId = tokenKeyDigester.digest(rsTokenDto.getAccessToken());
        final AccessToken myAccessToken = accessTokenRepository.findAccessToken(tokenId);


        for (RSAuthValidator authValidator : authValidators) {
            if (authValidator.support(rsTokenDto)) {
                final boolean validate = authValidator.validate(rsTokenDto, myAccessToken);
                if (!validate) {
                    LOG.debug("Valid failed when call: {}", authValidator);
                    return new UsernameDto(ErrorNumber.TOKEN_WRONG);
                }
            }
        }

        LOG.debug("Successful validate RSTokenDto: {}", rsTokenDto);
        return new UsernameDto(formatRPUserName(myAccessToken.username()));
    }
    
	private String formatRPUserName(String username) {
		if (null != username) {
			return username.split("@")[1];
		}
		return null;
	}

}
