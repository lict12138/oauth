
package com.tencent.oauth.service.busincess;


import com.tencent.commons.utils.PasswordHandler;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.security.Privilege;
import com.tencent.oauth.domain.security.UserRepository;
import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.domain.security.SecurityUtils;
import com.tencent.oauth.service.dto.user.UserFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2016/08/26
 * @author Shaofei Du
 */
public class CreateUserHandler {


    private static final Logger LOG = LoggerFactory.getLogger(CreateUserHandler.class);

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private UserFormDto formDto;

    public CreateUserHandler(UserFormDto formDto) {
        this.formDto = formDto;
    }

    public String handle() {

        final String username = formDto.getUsername();
        final String pass = PasswordHandler.encryptPassword(formDto.getPassword(), username);
        User user = new User(username, pass);
        user.getPrivileges().addAll(Privilege.availableEmpPrivileges());


        userRepository.saveUser(user);
        LOG.debug("{}|Create User: {}", SecurityUtils.currUser().username(), user);

        return user.getUuid();
    }
}
