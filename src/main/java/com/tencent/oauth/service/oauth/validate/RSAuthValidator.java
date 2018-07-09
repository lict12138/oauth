
package com.tencent.oauth.service.oauth.validate;


import com.tencent.oauth.domain.oauth.AccessToken;
import com.tencent.oauth.service.dto.RSTokenDto;

/**
 * 2015/12/8
 *
 * @author bobzbfeng
 */

public interface RSAuthValidator {


    boolean support(RSTokenDto tokenDto);

    /*
    * True is validated, otherwise , false
    * */
    boolean validate(RSTokenDto tokenDto, AccessToken accessToken);

}