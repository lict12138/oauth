package com.tencent.oauth.domain.security;

import java.util.Arrays;
import java.util.List;

public enum Privilege {


    SYSTEM_ACCOUNT("系统账号"),
    SYSTEM_ROOT("管理员"),

    MANUFACTURER_ACCOUNT("厂商账号"),

    //**** OAuth2  **********************************************************

    APPLICATION_CLIENT("application_client");

    private String label;

    Privilege(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return name();
    }

    /*
    管理员角色
     */
    public static List<Privilege> availablePrivileges() {
        return Arrays.asList(
                SYSTEM_ACCOUNT,
                SYSTEM_ROOT
        );
    }

    /*
    注册用户角色
     */
    public static List<Privilege> availableEmpPrivileges() {
        return Arrays.asList(
                MANUFACTURER_ACCOUNT
        );
    }
}
