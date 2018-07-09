/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web.controller;

import com.tencent.iot.service.PublicService;
import com.tencent.oauth.service.dto.user.UserFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author bobzbfeng
 */
@Controller
@RequestMapping("/public/register/")
public class RegisterController {

    @Autowired
    private PublicService publicService;

    @RequestMapping(value = "account/form",method = RequestMethod.GET)
    private String pageForm(Model model){
        model.addAttribute("formDto", new UserFormDto());
        return "public/register/account_register_form";
    }

    @RequestMapping(value = "account/form",method = RequestMethod.POST)
    private String plusUser(@ModelAttribute("formDto") @Valid UserFormDto formDto,BindingResult result,Model model){
        if(result.hasErrors()){
            return "public/register/account_register_form";
        }
        publicService.registerAccount(formDto);
        model.addAttribute("alert","success");
        return "redirect:../success?username="+formDto.getUsername();
    }

    @RequestMapping("success")
    private String registerSuccessPage(String username,Model model){
        model.addAttribute("name",username);
        return "public/register/register_success_page";
    }
}