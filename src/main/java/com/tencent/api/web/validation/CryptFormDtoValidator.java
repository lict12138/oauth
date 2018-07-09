/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.web.validation;

import com.tencent.api.service.dto.CryptFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author bobzbfeng
 */
@Component
public class CryptFormDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CryptFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hid", null, "Hid can't be null");
    }
}