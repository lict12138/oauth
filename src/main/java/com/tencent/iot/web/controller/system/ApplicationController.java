/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web.controller.system;

import com.tencent.iot.service.SystemService;
import com.tencent.iot.service.dto.AccountDto;
import com.tencent.iot.service.dto.ApplicationDto;
import com.tencent.iot.service.dto.ApplicationDtoPaginated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author bobzbfeng
 */
@Controller
@RequestMapping("/system/application/")
public class ApplicationController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "list")
    public String list(ApplicationDtoPaginated paginated,Model model){
        paginated = systemService.loadApplicationPaginated(paginated);
        model.addAttribute("paginated",paginated);
        return "system/application_list";
    }

    /*
   账号审核页面
    */
    @RequestMapping(value = "approve/page_{uuid}")
    public String approvePage(@PathVariable String uuid,Model model){
        ApplicationDto applicationDto = systemService.loadApplicationDtoByUuid(uuid);
        model.addAttribute("dto",applicationDto);
        return "system/application_approve_page";
    }

    @RequestMapping(value = "approve_{uuid}",method = RequestMethod.POST)
    public String approve(@PathVariable String uuid,Model model){
        systemService.approveApplicationByUuid(uuid);
        model.addAttribute("alert","approveSuccess");
        return "redirect:list";
    }

    @RequestMapping(value = "reject_{uuid}",method = RequestMethod.POST)
    public String reject(@PathVariable String uuid,Model model){
        systemService.rejectApplicationRegisterByUuid(uuid);
        model.addAttribute("alert","rejectSuccess");
        return "redirect:../accounts";
    }
}