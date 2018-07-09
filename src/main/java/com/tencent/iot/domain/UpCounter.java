/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.domain;

import java.io.Serializable;

/**
 * @author bobzbfeng
 * 上行计数器
 */
public class UpCounter implements Serializable{

    private static final long serialVersionUID = 3785810528490610665L;
    private String hid;

    private String counter;

    public UpCounter() {
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }
}