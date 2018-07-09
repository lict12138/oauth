/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web.validation;

import com.tencent.iot.service.dto.ApplicationFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author bobzbfeng
 */
@Component
public class ApplicationFormDtoValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return ApplicationFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "applicationName", null, "请输入应用名称！");
    }
}