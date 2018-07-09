/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.web.controller;

import com.tencent.api.service.RegisterService;
import com.tencent.api.service.dto.RegisterFormDto;
import com.tencent.api.service.dto.RegisterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author bobzbfeng
 * 该接口受OAuth保护 ，AS来源于 QkeyServer
 */
@Controller
@RequestMapping(value = "/api/register/")
public class SERegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "action",method = RequestMethod.POST)
    @ResponseBody
    public RegisterResponseDto register(@RequestBody @Valid RegisterFormDto formDto,BindingResult result){
        if(result.hasErrors()){
            return new RegisterResponseDto();
        }
        return registerService.register(formDto);
    }
}