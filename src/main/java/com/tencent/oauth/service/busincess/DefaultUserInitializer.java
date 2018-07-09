
package com.tencent.oauth.service.busincess;


import com.tencent.commons.utils.PasswordHandler;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.security.AccountStatus;
import com.tencent.oauth.domain.security.UserRepository;
import com.tencent.oauth.domain.security.Privilege;
import com.tencent.oauth.domain.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/12/21
 * <p/>
 * <p/>
 * When the system firstly startup, checking and initialed default-user.
 *
 * @author bobzbfeng
 */
public class DefaultUserInitializer {


    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserInitializer.class);

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    public DefaultUserInitializer() {
    }

    public String initial() {

        long count = 0;//userRepository.countUsers();
        if (count > 0) {
            return "404";
        }

        String username = "IOT";
        String password = PasswordHandler.encryptPassword("IOT@TUSI", username);
        User user = new User(username, password);
        user.getPrivileges().addAll(Privilege.availablePrivileges());
        user.setEnabled(true);
        user.setSystemAccount(true);
        user.setAccountStatus(AccountStatus.NONE);

        userRepository.saveUser(user);
        LOG.info("System initialed default-user: {}", user);

        return user.username();
    }


}
