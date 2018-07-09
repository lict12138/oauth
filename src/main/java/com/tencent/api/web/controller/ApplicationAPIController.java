/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.web.controller;

import com.tencent.api.service.ApplicationService;
import com.tencent.api.service.dto.CryptFormDto;
import com.tencent.api.service.dto.CryptResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author bobzbfeng
 */
@Controller
@RequestMapping("/api/application/")
public class ApplicationAPIController {

    @Autowired
    private ApplicationService applicationService;


    @RequestMapping("crypt")
    @ResponseBody
    public CryptResponseDto action(@RequestBody @Valid CryptFormDto formDto,BindingResult result){
        if(result.hasErrors()){
            return new CryptResponseDto("params error");
        }
        return applicationService.cryptAction(formDto);
    }
}