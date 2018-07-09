/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.service.business;

import com.tencent.commons.utils.paginated.PaginatedLoader;
import com.tencent.commons.web.context.BeanProvider;
import com.tencent.iot.service.dto.AccountDto;
import com.tencent.iot.service.dto.AccountPaginated;
import com.tencent.oauth.domain.security.User;
import com.tencent.oauth.domain.security.UserRepository;

import java.util.List;
import java.util.Map;

/**
 * @author bobzbfeng
 */
public class AccountPaginatedLoader {

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    private AccountPaginated paginated;

    public AccountPaginatedLoader(AccountPaginated paginated){
        this.paginated = paginated;
    }

    public AccountPaginated load(){

        Map<String, Object> queryMap = paginated.queryMap();

        return paginated.load(new PaginatedLoader<AccountDto>() {
            @Override
            public List<AccountDto> loadList() {
                List<User> userList = userRepository.findUserPaginated(queryMap);
                return AccountDto.toDtos(userList);
            }

            @Override
            public long loadTotalSize() {
                return userRepository.totalUserPaginated(queryMap);
            }
        });
    }
}