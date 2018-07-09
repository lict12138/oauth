/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web.controller.system;

import com.tencent.commons.domain.AbstractDto;
import com.tencent.iot.service.SystemService;
import com.tencent.iot.service.dto.AccountDto;
import com.tencent.iot.service.dto.AccountPaginated;
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
@RequestMapping("/system/")
public class SystemController {

    @Autowired
    private SystemService systemService;

    /*
    管理员首页
     */
    @RequestMapping(value = "index")
    public String index(){
        return "system/index";
    }


    /**
    *账号列表
     */
    @RequestMapping(value = "accounts")
    public String accounts(AccountPaginated paginated,Model model){
        paginated = systemService.loadAccountPaginated(paginated);
        model.addAttribute("paginated",paginated);
        return "system/account_list";
    }

    /*
    账号审核页面
     */
    @RequestMapping(value = "accounts/approve/page_{uuid}")
    public String approvePage(@PathVariable String uuid,Model model){
        AccountDto accountDto = systemService.loadAccountDtoByUuid(uuid);
        model.addAttribute("dto",accountDto);
        return "system/account_approve_page";
    }

    @RequestMapping(value = "accounts/approve_{uuid}",method = RequestMethod.POST)
    public String approve(@PathVariable String uuid,Model model){
        systemService.approveAccountByUuid(uuid);
        model.addAttribute("alert","approveSuccess");
        return "redirect:../accounts";
    }

    @RequestMapping(value = "accounts/reject_{uuid}",method = RequestMethod.POST)
    public String reject(@PathVariable String uuid,Model model){
        systemService.rejectAccountRegisterByUuid(uuid);
        model.addAttribute("alert","rejectSuccess");
        return "redirect:../accounts";
    }
}