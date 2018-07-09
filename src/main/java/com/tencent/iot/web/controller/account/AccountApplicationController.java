/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.web.controller.account;

import com.tencent.iot.service.AccountService;
import com.tencent.iot.service.dto.ApplicationClientDetailsDto;
import com.tencent.iot.service.dto.ApplicationDtoPaginated;
import com.tencent.iot.service.dto.ApplicationFormDto;
import com.tencent.iot.service.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bobzbfeng
 */
@Controller
@RequestMapping(value = "/account/application/")
public class AccountApplicationController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("list")
    public String list(ApplicationDtoPaginated paginated,Model model){
        paginated = accountService.loadMyApplicationDtoPaginated(paginated);
        model.addAttribute("paginated",paginated);
        return "account/account_application_list";
    }

    @RequestMapping(value = "create/form",method = RequestMethod.GET)
    public String plusPage(Model model){
        model.addAttribute("formDto",new ApplicationFormDto());
        return "account/application_form";
    }

    @RequestMapping(value = "create/form",method = RequestMethod.POST)
    public String plusApplication(@ModelAttribute("formDto") @Valid ApplicationFormDto formDto,BindingResult result,Model model){
        if(result.hasErrors()){
            return "account/application_form";
        }
        accountService.createApplication(formDto);
        model.addAttribute("alert","createSuccess");
        return "redirect:../list";
    }

    @RequestMapping(value = "model/client_details/{uuid}")
    public String appClientDetailsModel(@PathVariable String uuid,Model model){
        ApplicationClientDetailsDto detailsDto = accountService.loadApplicationClientDetailsByAppUuid(uuid);
        model.addAttribute("dto",detailsDto);
        return "account/application_client_details_page";
    }

    @RequestMapping(value = "update_token_valid_time")
    @ResponseBody
    public ResultDto updateTokenValidTime(@RequestBody ApplicationClientDetailsDto formDto){
        return accountService.updateApplicationTokenTime(formDto);
    }
}