package com.tencent.oauth.domain.security;


import java.util.List;
import java.util.Map;

public interface UserRepository{
    void saveUser(User user);

    User findByUsername(String username);

    void removeUser(User user);

    User findByGuid(String guid);

    User currentUser();

    List<User> findUserPaginated(Map<String, Object> map);

    long totalUserPaginated(Map<String, Object> map);

    long countUsers();

    void updateUserStatus(User user);
}
