/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bobzbfeng
 */
@Controller
@RequestMapping("/account/")
public class AccountController {

    @RequestMapping(value = "index")
    public String index(){
        return "account/index";
    }
}