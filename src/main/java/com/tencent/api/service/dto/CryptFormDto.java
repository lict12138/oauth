/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.dto;

import java.io.Serializable;
import static com.tencent.commons.web.WebUtils.*;

/**
 * @author bobzbfeng
 */
public class CryptFormDto implements Serializable{
    private static final long serialVersionUID = -2649764309791800956L;

    int type = -1;
    String hid;
    String data;
    String result;

    public CryptFormDto() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = paramFilter(result);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = paramFilter(data);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = paramFilter(hid);
    }
}