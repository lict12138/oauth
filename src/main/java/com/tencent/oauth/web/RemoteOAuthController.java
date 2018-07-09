
package com.tencent.oauth.web;


import com.tencent.commons.web.WebUtils;
import com.tencent.oauth.service.OauthService;
import com.tencent.oauth.service.dto.RSTokenDto;
import com.tencent.oauth.service.dto.UsernameDto;
import com.tencent.oauth.infrastructure.oauth.ErrorNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2015/11/7
 * <p/>
 * Call by the Resource Server client.
 * For validation the token, clientId or resourceId..
 *
 * @author bobzbfeng
 */
@Controller
@RequestMapping("/public/oauth/")
public class RemoteOAuthController {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteOAuthController.class);

    @Autowired
    private OauthService oauthService;


    /*
    * Restful API
    *
    * Validation accessToken, clientId(optional), resourceId(optional)
    *
    * */
    @RequestMapping(value = "token_validation", method = RequestMethod.POST)
    @ResponseBody
    public UsernameDto validationToken(@RequestBody RSTokenDto rsTokenDto) {
        LOG.debug("Call 'validation', RSTokenDto: {}, IP: {}", rsTokenDto, WebUtils.getIp());
        if (!rsTokenDto.available()) {
            return new UsernameDto(ErrorNumber.REQUEST_METHOD_ERROR);
        }
        return oauthService.validateRSTokenDto(rsTokenDto);
    }


}
