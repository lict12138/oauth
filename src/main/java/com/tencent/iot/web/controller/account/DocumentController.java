/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web.controller.account;

import com.tencent.oauth.domain.security.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bobzbfeng
 */
@Controller
public class DocumentController {


    @RequestMapping({"/system/document","/account/document"})
    public String docPage(Model model){
        model.addAttribute("adminAccount", SecurityUtils.currUser().isAdmin());
        return "doc/"+(SecurityUtils.currUser().isAdmin()?"document_page":"account_document_page");
    }
}