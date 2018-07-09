package com.tencent.commons.utils;

import org.testng.annotations.Test;

public class PasswordHandlerTest {

    @Test
    public void testHash(){
        final String salt = "CAS";
        final String clearPwd = "CAS@DEMO";
        String encryptPassword = PasswordHandler.encryptPassword(clearPwd, salt);

        System.out.println(encryptPassword);

    }

}