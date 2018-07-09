/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.dto;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import static com.tencent.commons.web.WebUtils.*;

/**
 * @author bobzbfeng
 */
public class RegisterFormDto implements Serializable{
    private static final long serialVersionUID = 1282612515645278474L;

    String	hid;
    String	rsapubkey;
    String	eccpubkey;
    int		algType;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = paramFilter(hid);
    }

    public String getRsapubkey() {
        return rsapubkey;
    }

    public void setRsapubkey(String rsapubkey) {
        this.rsapubkey = paramFilter(rsapubkey);
    }

    public String getEccpubkey() {
        return eccpubkey;
    }

    public void setEccpubkey(String eccpubkey) {
        this.eccpubkey = paramFilter(eccpubkey);
    }

    public int getAlgType() {
        return algType;
    }

    public void setAlgType(int algType) {
        this.algType = algType;
    }


    public boolean emptyRsaPubKey(){
        return StringUtils.isEmpty(rsapubkey)||StringUtils.isBlank(rsapubkey);
    }
}