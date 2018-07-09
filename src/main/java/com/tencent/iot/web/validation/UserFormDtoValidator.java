/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web.validation;

import com.tencent.iot.service.PublicService;
import com.tencent.oauth.service.dto.user.UserFormDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author bobzbfeng
 */
@Component
public class UserFormDtoValidator implements Validator{

    @Autowired
    private PublicService publicService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserFormDto formDto = (UserFormDto)target;
        validateUsername(formDto,errors);
        validatePassword(formDto,errors);
    }

    private void validatePassword(UserFormDto formDto, Errors errors) {
        final String password = formDto.getPassword();
        if(StringUtils.isEmpty(password)||StringUtils.isBlank(password)){
            errors.rejectValue("password",null,"请输入密码!");
        }else{
            boolean shorted = password.length() < 8;
            if(shorted){
                errors.rejectValue("password",null,"密码长度至少8位！");
            }
            checkRePassword(formDto,errors);
        }
    }

    private void checkRePassword(UserFormDto formDto, Errors errors) {
        final String password = formDto.getPassword();
        String rePassword = formDto.getRePassword();
        if(StringUtils.isEmpty(rePassword)||StringUtils.isBlank(rePassword)){
            errors.rejectValue("rePassword",null,"请再次确认你的密码!");
        }else{
            if(!rePassword.equals(password)){
                errors.rejectValue("rePassword",null,"前后输入密码不一致！");
            }
        }
    }

    private void validateUsername(UserFormDto formDto, Errors errors) {
        final String username = formDto.getUsername();
        if(StringUtils.isEmpty(username)||StringUtils.isBlank(username)){
            errors.rejectValue("username",null,"请输入用户名（登录账号）!");
        }else{
            boolean existed = checkUsernameExisted(username);
            if(existed){
                errors.rejectValue("username",null,"用户名(登录账号)已存在，请更换并重新输入");
            }
        }
    }

    private boolean checkUsernameExisted(String username) {
        return publicService.isExistedUsername(username);
    }
}