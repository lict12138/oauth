/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web;

import com.tencent.oauth.service.UserService;
import com.tencent.tusi.sgx.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bobzbfeng
 */
@Controller
public class StartupController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/login","/"})
    private String login(){
        return "login";
    }

    @RequestMapping("/public/initial_user")
    @ResponseBody
    private String initialUser(){

        return userService.initialDefaultUser();
    }




}