/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.api.service.business.crypt;

import com.tencent.api.service.dto.CryptFormDto;
import com.tencent.api.service.dto.CryptResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bobzbfeng
 * 策略模式，根据 type 标识位不同，对应不同的业务处理
 * 0 == 签名验签
 * 1 == 加密
 * 2 == 解密
 *
 */
public class CryptHandler {

    private CryptFormDto formDto;

    private List<CryptResolver> cryptResolvers = new ArrayList<>();

    public CryptHandler(CryptFormDto formDto){
        this.formDto = formDto;
        initialCryptResolvers();
    }

    public CryptResponseDto handle(){
        for(CryptResolver resolver:cryptResolvers){
            if(resolver.support(formDto.getType())){
                try {
                    return resolver.resolve(formDto);
                }catch (Exception exception){
                    //统一捕获异常信息
                    return new CryptResponseDto();
                }
            }
        }

        throw new IllegalArgumentException("Not support Crypt Type --"+formDto.getType());
    }

    private void initialCryptResolvers() {
        cryptResolvers.add(new VerifyCryptResolver());
        cryptResolvers.add(new EncryptCryptResolver());
        cryptResolvers.add(new DecryptCryptResolver());
    }
}