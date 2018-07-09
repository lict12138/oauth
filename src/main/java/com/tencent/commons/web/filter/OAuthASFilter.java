/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.web.filter;

import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author bobzbfeng
 */
public class OAuthASFilter implements Filter{
    private static final Logger LOG = LoggerFactory.getLogger(OAuthASFilter.class);

    private static final String URL_SEPARATOR = "/";

    // URI:   /remote/token/validate/{accessToken}/{clientId}
    // or  /remote/token/validate/{accessToken}
    public static final String VALIDATE_ACCESS_TOKEN_URI = "validate.access-token.uri";

    public static final String CLIENT_ID_PARAMETER = "client.id";

    public static final String ACCESS_TOKEN_PARAMETER = "access_token";

    public static final String AUTHORIZATION ="Authorization";
    protected String validateAccessTokenUri;
    //null is available
    protected String clientId;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //this.validateAccessTokenUri = PropertyUtils.getRemoteUrl();
        this.validateAccessTokenUri =  filterConfig.getInitParameter(VALIDATE_ACCESS_TOKEN_URI);
        if (StringUtils.isEmpty(this.validateAccessTokenUri)) {
            throw new ServletException("Invalid config '" + VALIDATE_ACCESS_TOKEN_URI + "', please checking...");
        }
        if (!this.validateAccessTokenUri.endsWith(URL_SEPARATOR)) {
            this.validateAccessTokenUri += URL_SEPARATOR;
        }
        //LOG.debug("Initial validateAccessTokenUri: {}", this.validateAccessTokenUri);

        // clientId , optional
        String clientIdTemp = filterConfig.getInitParameter(CLIENT_ID_PARAMETER);
        if (StringUtils.isNotEmpty(clientIdTemp)) {
            this.clientId = clientIdTemp;
            //LOG.debug("Initial clientId: {}", this.clientId);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String accessToken = getAccessToken(req);
        String path =  req.getRequestURI();
        if(path.contains("RegisterRequest")||path.contains("AuthenticateRequest")){
            System.out.println(path);
        }

        //String accessToken = servletRequest.getParameter(ACCESS_TOKEN_PARAMETER);
        if (!validateAccessToken(accessToken)) {
            responseInvalidAccessToken(accessToken, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    private String getAccessToken(HttpServletRequest req){
        String at = req.getHeader(AUTHORIZATION);
        if(null!=at){
            if(at.contains("bearer")||at.contains("Bearer")){
                return at.substring(6).trim();
            }else{
                return at.trim();
            }
        }
       return req.getParameter(ACCESS_TOKEN_PARAMETER);
    }

    /*
    {
"error": "unauthorized",
"error_description": "An Authentication object was not found in the SecurityContext"
}
     */
    //JSON response
    protected void responseInvalidAccessToken(String accessToken, ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        final PrintWriter writer = response.getWriter();
        JSONObject result = new JSONObject();
        result.put("error","unauthorized");
        result.put("error_description",StringUtils.isEmpty(accessToken)?"" +
                "An Authentication object was not found in the SecurityContext":
                "Invalid access token: "+accessToken);

        writer.print(result.toString());
        writer.flush();
    }


    protected boolean validateAccessToken(String accessToken) throws IOException {
        String fullUri = requestUri(accessToken);
        //LOG.debug("Validate '{}' full uri: {}", ACCESS_TOKEN_PARAMETER, fullUri);
        boolean success = false;

        try {
            URL url = new URL(fullUri);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //POST request
            connection.setRequestMethod("POST");

            if (connection.getResponseCode() == 200) {
                final String result = IOUtils.toString(connection.getInputStream());
                //Just checking true or false
                success = "true".equalsIgnoreCase(result);
            }
        } catch (Exception e) {
            //LOG.warn("Validate '" + ACCESS_TOKEN_PARAMETER + "' error", e);
        }
        //LOG.debug("Validate '{}' result: {}", ACCESS_TOKEN_PARAMETER, success);

        return success;
    }

    /**
     * http://localhost:8080/som/remote/token/validate/{accessToken}/{clientId}
     * or
     * http://localhost:8080/som/remote/token/validate/{accessToken}
     *
     * @param accessToken accessToken
     * @return URI
     */
    protected String requestUri(String accessToken) {
        if (StringUtils.isEmpty(this.clientId)) {
            return this.validateAccessTokenUri + accessToken;
        }
        return this.validateAccessTokenUri + accessToken + URL_SEPARATOR + clientId;
    }

    @Override
    public void destroy() {
        //nothing
    }
}