package com.tencent.api.service.business.register;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SERegisterTest {

    @Test
    public void test(){
        int downCounter = 1;
        String format = String.format("%08X", downCounter);
        System.out.println(format);
    }
}