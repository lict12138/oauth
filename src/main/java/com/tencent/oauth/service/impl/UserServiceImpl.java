package com.tencent.oauth.service.impl;


import com.tencent.commons.utils.paginated.PaginatedLoader;
import com.tencent.oauth.domain.security.UserRepository;
import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.domain.security.IdsUserDetails;
import com.tencent.oauth.domain.security.SecurityUtils;
import com.tencent.oauth.service.UserService;
import com.tencent.oauth.service.busincess.CreateUserHandler;
import com.tencent.oauth.service.busincess.DefaultUserInitializer;
import com.tencent.oauth.service.busincess.MySettingDtoUpdater;
import com.tencent.oauth.service.dto.user.MySettingDto;
import com.tencent.oauth.service.dto.user.UserFormDto;
import com.tencent.oauth.service.dto.user.UserPaginated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {


    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isExistedUsername(String username) {
        final User user = userRepository.findByUsername(username);
        return user != null;
    }


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public UserPaginated loadUserPaginated(UserPaginated paginated) {
        final Map<String, Object> map = paginated.queryMap();
        return paginated.load(new PaginatedLoader<User>() {
            @Override
            public List<User> loadList() {
                return userRepository.findUserPaginated(map);
            }

            @Override
            public long loadTotalSize() {
                return userRepository.totalUserPaginated(map);
            }
        });
    }

    @Override
    public String createUser(UserFormDto formDto) {
        CreateUserHandler handler = new CreateUserHandler(formDto);
        return handler.handle();
    }

    @Override
    public void archiveUserByGuid(String guid) {
        final User user = userRepository.findByGuid(guid);
        LOG.debug("{}|Delete User: {}", SecurityUtils.currUser().username(), user);
        userRepository.removeUser(user);
    }

    @Override
    public void updateMySetting(MySettingDto formDto) {
        MySettingDtoUpdater updater = new MySettingDtoUpdater(formDto);
        updater.update();
    }


    @Override
    public User getUserByGuid(String guid) {
        return userRepository.findByGuid(guid);
    }

    @Override
    public String initialDefaultUser() {
        DefaultUserInitializer initializer = new DefaultUserInitializer();
        return initializer.initial();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found by username: " + username);
        }
        return new IdsUserDetails(user);
    }
}
