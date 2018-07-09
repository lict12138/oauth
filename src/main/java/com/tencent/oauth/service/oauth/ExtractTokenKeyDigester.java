
package com.tencent.oauth.service.oauth;

/**
 * 2015/11/9
 *
 * @author bobzbfeng
 */
public interface ExtractTokenKeyDigester {


    String digest(String tokenValue);


}
