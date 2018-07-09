package com.tencent.oauth.domain.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class IdsUserDetails implements UserDetails {


    /**
     *
     */
    private static final long serialVersionUID = -2513888014140057691L;

    protected static final String ROLE_PREFIX = "ROLE_";

    protected User user;

    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public IdsUserDetails() {

    }

    public IdsUserDetails(User user) {
        this.user = user;
        initialAuthorities();
    }

    private void initialAuthorities() {
        final Set<Privilege> privileges = user.getPrivileges();
        this.grantedAuthorities.addAll(privileges.stream().map(privilege -> new SimpleGrantedAuthority(ROLE_PREFIX + privilege)).collect(Collectors.toList()));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }


    public User user() {
        return this.user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdsUserDetails)) return false;

        IdsUserDetails that = (IdsUserDetails) o;

        if (grantedAuthorities != null ? !grantedAuthorities.equals(that.grantedAuthorities) : that.grantedAuthorities != null)
            return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (grantedAuthorities != null ? grantedAuthorities.hashCode() : 0);
        return result;
    }
}
