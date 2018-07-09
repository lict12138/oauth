/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.utils.jdbc;

import com.tencent.iot.domain.UpCounter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bobzbfeng
 */
public class UpCounterRowMapper implements RowMapper<UpCounter>{
    @Override
    public UpCounter mapRow(ResultSet rs, int rowNum) throws SQLException {
        UpCounter upCounter = new UpCounter();
        upCounter.setHid(rs.getString("c_hid"));
        upCounter.setCounter(rs.getString("c_counter"));
        return upCounter;
    }
}