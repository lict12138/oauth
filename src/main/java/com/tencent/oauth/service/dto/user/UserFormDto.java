
package com.tencent.oauth.service.dto.user;


import com.tencent.oauth.domain.security.AccountStatus;
import com.tencent.oauth.domain.security.Privilege;
import com.tencent.oauth.domain.security.User;

import java.io.Serializable;
import static com.tencent.commons.web.WebUtils.*;

/**
 * 2015/12/19
 *
 * @author bobzbfeng
 */
public class UserFormDto implements Serializable {
    private static final long serialVersionUID = -8251954993624473451L;


    private String username;

    private String password;

    private String rePassword;



    public UserFormDto() {
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username =paramFilter( username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = paramFilter(password);
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = paramFilter(rePassword);
    }


    public User createUser(){
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setAccountStatus(AccountStatus.SUBMIT);
        user.getPrivileges().add(Privilege.MANUFACTURER_ACCOUNT);

        return user;
    }
}
