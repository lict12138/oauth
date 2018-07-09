package com.tencent.oauth.domain.security;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

/**
 * @author bobzbfeng
 */
public class SpringSecurityHolder implements SecurityHolder {

    public static ThreadLocal<SecurityContext> securityContextThreadLocal = new ThreadLocal<>();


    public static void setSecurityContext(SecurityContext securityContext) {
        Assert.notNull(securityContext);
        securityContextThreadLocal.set(securityContext);
    }

    public static SecurityContext getSecurityContext() {
        return securityContextThreadLocal.get();
    }


    public SpringSecurityHolder() {
    }


    public IdsUserDetails currentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof IdsUserDetails) {
            return (IdsUserDetails) principal;
        }
        return null;
    }


    @Override
    public User currUser() {
        IdsUserDetails userDetails = currentUserDetails();
        return (userDetails != null ? userDetails.user() : null);
    }
}
