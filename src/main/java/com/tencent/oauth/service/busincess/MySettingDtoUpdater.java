
package com.tencent.oauth.service.busincess;


import com.tencent.commons.web.context.BeanProvider;
import com.tencent.oauth.domain.security.UserRepository;
import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.domain.security.SecurityUtils;
import com.tencent.oauth.service.dto.user.MySettingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/12/21
 *
 * @author bobzbfeng
 */
public class MySettingDtoUpdater {


    private static final Logger LOG = LoggerFactory.getLogger(MySettingDtoUpdater.class);

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private MySettingDto formDto;

    public MySettingDtoUpdater(MySettingDto formDto) {
        this.formDto = formDto;
    }

    public void update() {

        final User user = userRepository.currentUser();
        user.setPassword(formDto.getNewPassword());

       // userRepository.updateUserPassword(user);
        LOG.debug("{}|Update my-setting: change password", SecurityUtils.currUser().username());
    }
}
