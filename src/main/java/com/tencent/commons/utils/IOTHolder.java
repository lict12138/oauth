/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.utils;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author bobzbfeng
 */
public class IOTHolder implements InitializingBean{

    //系统维护版本号，同pom.xml 中的version
    public static final String VERSION = "1.0";

    public static final String MASTER_KEY = "masterkey";

    private static String cryotpServerIp;

    private static int cryptoServerPort;

    public static String cryotpServerIp() {
        return cryotpServerIp;
    }

    public static int cryptoServerPort() {
        return cryptoServerPort;
    }

    public static void setCryotpServerIp(String cryotpServerIp) {
        IOTHolder.cryotpServerIp = cryotpServerIp;
    }

    public static void setCryptoServerPort(int cryptoServerPort) {
        IOTHolder.cryptoServerPort = cryptoServerPort;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //do nothing
    }
}