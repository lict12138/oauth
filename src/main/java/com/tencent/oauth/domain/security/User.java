package com.tencent.oauth.domain.security;


import com.tencent.commons.domain.AbstractDomain;
import com.tencent.commons.utils.PasswordHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统账号
 *
 * @author bobzbfeng
 */

public class User extends AbstractDomain {


    private static final long serialVersionUID = -1958822224307828242L;

    private String username;

    private String password;


    private Set<Privilege> privileges = new HashSet<>();

    //账户是否可用
    private boolean enabled = false;

    //账户状态
    private AccountStatus accountStatus;

    //是否是系统账户
    private boolean systemAccount = false;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String originalPass) {
        this.password = PasswordHandler.encryptPassword(originalPass, this.username);
    }

    public void setEncryptedPassword(String encryptedPassword){
        this.password = encryptedPassword;
    }


    public void setPrivileges(List<Privilege> privileges) {
        this.privileges.addAll(privileges);
    }

    public String password() {
        return this.password;
    }

    public String username() {
        return this.username;
    }


    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", uuid='" + uuid + '\'' +
                ", privileges=" + privileges +
                '}';
    }

    public boolean isAdmin() {
        return this.privileges.contains(Privilege.SYSTEM_ROOT);
    }

    public boolean isNormalAccount() {
        return this.privileges.contains(Privilege.MANUFACTURER_ACCOUNT);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isSystemAccount() {
        return systemAccount;
    }

    public void setSystemAccount(boolean systemAccount) {
        this.systemAccount = systemAccount;
    }
}
