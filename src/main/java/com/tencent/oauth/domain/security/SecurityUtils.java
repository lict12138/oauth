package com.tencent.oauth.domain.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author bobzbfeng
 */
public class SecurityUtils {

    private static SecurityHolder securityHolder;

    /**
     * Get current login user guid.
     *
     * @return User guid or null
     */
    public static String currentUserGuid() {
        final User user = securityHolder.currUser();
        return (user != null ? user.getUuid() : null);
    }

    public static User currUser() {
        return securityHolder.currUser();
    }

    public void setSecurityHolder(SecurityHolder securityHolder) {
        SecurityUtils.securityHolder = securityHolder;
    }

    /**
     * Return current is logged or not.
     *
     * @return True is login,otherwise false
     */
    public static boolean isLogged() {
        return StringUtils.isNotBlank(currentUserGuid());
    }

    public static String currentClientId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        final Object principal = authentication.getPrincipal();

        if (authentication instanceof OAuth2Authentication &&
                (principal instanceof String || principal instanceof org.springframework.security.core.userdetails.User)) {
            final OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            return oAuth2Authentication.getName();
        }
        return null;
    }


}