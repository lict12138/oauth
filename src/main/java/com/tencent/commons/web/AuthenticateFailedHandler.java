/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.web;

import com.tencent.iot.domain.AuditLog;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bobzbfeng
 */
public class AuthenticateFailedHandler implements AuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String contentPath = httpServletRequest.getContextPath();

        String redirectUri = contentPath + "/login?authentication_error=";
        if(e instanceof DisabledException){
            redirectUri += "4";
        }else{
            redirectUri += "1";
        }
        String username = loginName(httpServletRequest);
        AuditLog.createLoginAuditLog(username,null,"登录失败");
        httpServletResponse.sendRedirect(redirectUri);

    }

    private String loginName(HttpServletRequest request) {
        return request.getParameter("username");
    }
}