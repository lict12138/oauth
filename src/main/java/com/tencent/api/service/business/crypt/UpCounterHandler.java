/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.business.crypt;

import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.domain.UpCounter;
import com.tencent.iot.infrastructure.impl.UpCounterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bobzbfeng
 */
public class UpCounterHandler {

    private static Logger LOG = LoggerFactory.getLogger(UpCounterHandler.class);

    private transient UpCounterRepository upCounterRepository = BeanProvider.getBean(UpCounterRepository.class);

    public UpCounterHandler(){
    }

    public boolean insert(String hid,String counter){
        UpCounter upCounter = upCounterRepository.findUpCounterByHidAndCounter(hid, counter);

        if(null!=upCounter){
            // upCounter exists
            LOG.debug("Failed,UpCounter Existed by Hid ({}) with counter ({})",hid,counter);
            return false;
        }
        upCounterRepository.saveUpCounter(hid,counter);
        LOG.debug("Success,Created UpCounter by Hid ({}) with counter ({})",hid,counter);
        return true;
    }
}