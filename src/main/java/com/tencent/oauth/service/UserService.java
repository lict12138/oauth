package com.tencent.oauth.service;


import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.service.dto.user.MySettingDto;
import com.tencent.oauth.service.dto.user.UserFormDto;
import com.tencent.oauth.service.dto.user.UserPaginated;

public interface UserService {
    boolean isExistedUsername(String username);

    User getUserByUsername(String username);

    UserPaginated loadUserPaginated(UserPaginated paginated);

    String createUser(UserFormDto formDto);

    void archiveUserByGuid(String guid);

    void updateMySetting(MySettingDto formDto);

    User getUserByGuid(String guid);

    String initialDefaultUser();
}
