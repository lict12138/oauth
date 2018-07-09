/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.utils.jdbc;

import com.tencent.commons.web.context.BeanProvider;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * @author bobzbfeng
 */
public class AbstractJDBCSupport {

    protected String addPagination(String sql,Map<String,Object> map){
        StringBuffer buffer = new StringBuffer(sql);
        buffer.append(" order by c_create_time desc");
        buffer.append(" limit ").append(map.get("startIndex") + "," + map.get("perPageSize"));
        return buffer.toString();
    }

}