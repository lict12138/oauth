/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.web;

import com.tencent.iot.domain.AuditLog;
import com.tencent.oauth.domain.security.IdsUserDetails;
import com.tencent.oauth.domain.security.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bobzbfeng
 */
public class AuthenticateSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        IdsUserDetails userDetails = (IdsUserDetails) authentication
                .getPrincipal();
        User user = userDetails.user();

        final String contextPath = request.getContextPath();
        String redirectUri = null;
        if(null == user) {
            throw new IllegalStateException("Unsupport User redirect, user = "
                    + user);
        }else{
            if(user.isNormalAccount()){
                redirectUri = contextPath +"/account/index";
            }else {
                redirectUri = contextPath + "/system/index";
            }
        }
        AuditLog.createLoginAuditLog(user.getUsername(),user.getUuid(),"登录成功");
        httpServletResponse.sendRedirect(redirectUri);
    }
}