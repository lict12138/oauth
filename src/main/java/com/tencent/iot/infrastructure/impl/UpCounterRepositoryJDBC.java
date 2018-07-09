/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.infrastructure.impl;

import com.tencent.commons.utils.jdbc.AbstractJDBCSupport;
import com.tencent.commons.utils.jdbc.UpCounterRowMapper;
import com.tencent.iot.domain.UpCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bobzbfeng
 */
@Repository("upCounterRepository")
public class UpCounterRepositoryJDBC extends AbstractJDBCSupport implements UpCounterRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UpCounterRowMapper upCounterRowMapper = new UpCounterRowMapper();

    @Override
    public UpCounter findUpCounterByHidAndCounter(String hid, String counter) {
        String sql = "select * from t_up_counter where c_hid = ? and c_counter = ?";
        List<UpCounter> counters = jdbcTemplate.query(sql, new Object[]{hid, counter}, upCounterRowMapper);
        if(counters.isEmpty()){
            return null;
        }
        return counters.get(0);
    }

    @Override
    public void saveUpCounter(String hid, String counter) {
        String sql = "insert into t_up_counter (c_hid,c_counter) values (?,?)";
        this.jdbcTemplate.update(sql,ps ->{
            ps.setString(1,hid);
            ps.setString(2,counter);
        });
    }


}