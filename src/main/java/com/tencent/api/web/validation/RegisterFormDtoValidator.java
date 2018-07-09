/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.web.validation;

import com.tencent.api.service.dto.RegisterFormDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author bobzbfeng
 */
@Component
public class RegisterFormDtoValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hid", null, "Hid can't be null");
        RegisterFormDto formDto = (RegisterFormDto)target;
        validatePubKey(formDto,errors);
    }

    private void validatePubKey(RegisterFormDto formDto, Errors errors) {
        final String eccpubkey = formDto.getEccpubkey();
        if(StringUtils.isEmpty(eccpubkey)&&StringUtils.isBlank(eccpubkey)){
            errors.rejectValue("eccpubkey",null,"Empty ECC PubKey");
        }
    }
}