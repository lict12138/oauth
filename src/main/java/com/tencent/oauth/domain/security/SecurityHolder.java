package com.tencent.oauth.domain.security;


/**
 * @author bobzbfeng
 */
public interface SecurityHolder {

    IdsUserDetails currentUserDetails();

    User currUser();

}
