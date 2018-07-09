
package com.tencent.oauth.service.dto.user;


import com.tencent.commons.utils.paginated.DefaultPaginated;
import com.tencent.oauth.domain.security.User;

import java.util.Map;

/**
 * 2015/12/19
 *
 * @author bobzbfeng
 */
public class UserPaginated extends DefaultPaginated<User> {


    private String username;

    public UserPaginated() {
    }

    @Override
    public Map<String, Object> queryMap() {
        final Map<String, Object> map = super.queryMap();
        map.put("username", username);
        return map;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
