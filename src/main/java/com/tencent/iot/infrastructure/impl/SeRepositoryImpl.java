/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.iot.infrastructure.impl;

import com.tencent.commons.utils.DateUtils;
import com.tencent.commons.utils.jdbc.SERowMapper;
import com.tencent.iot.domain.SE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bobzbfeng
 */
@Repository("seRepository")
public class SeRepositoryImpl implements SeRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SERowMapper seRowMapper = new SERowMapper();

    @Override
    public SE findSEByHid(String hid) {
        String sql = "select * from t_seInfo where c_hid = ? ";
        List<SE> seList = jdbcTemplate.query(sql, new Object[]{hid}, seRowMapper);
        if(seList.isEmpty()){
            return null;
        }
        return seList.get(0);
    }

    @Override
    public void saveSE(SE se) {
        final String sql = " insert into t_seInfo(c_hid,c_eccpubkey,c_rsapubkey,c_downcounter,c_upseed,c_downseed,c_macseed,e_encryptedkey,c_algtype,c_dataTime,c_master_key_name) " +
                " values (?,?,?,?,?,?,?,?,?,?,?) ";
        this.jdbcTemplate.update(sql, ps -> {
            ps.setString(1,se.getHid());
            ps.setString(2,se.getEccpubkey());
            ps.setString(3,se.getRsapubkey());
            ps.setInt(4, se.getDownCounter());
            ps.setString(5,se.getUpSeed());
            ps.setString(6,se.getDownSeed());
            ps.setString(7,se.getMacSeed());
            ps.setString(8,se.getEncryptedkey());
            ps.setInt(9, se.getAlgType());
            ps.setString(10, DateUtils.toDateText(DateUtils.now()));
            ps.setString(11,se.getMasterKeyName());
        });
    }

    @Override
    public void updateSEDownCounterByHid(String hid, int downCounter) {
        String sql = "update t_seInfo set c_downcounter = ? where c_hid = ?";
        this.jdbcTemplate.update(sql,ps -> {
            ps.setInt(1, downCounter);
            ps.setString(2, hid);
        });
    }

    @Override
    public void updateSE(SE se) {
        String sql = "update t_seInfo set c_eccpubkey = ? , c_rsapubkey = ? ,c_upseed = ?," +
                "c_downseed = ?,c_macseed=?,e_encryptedkey = ?, c_algtype = ?,c_master_key_name = ? ,c_downcounter = ? where c_hid = ?";
        this.jdbcTemplate.update(sql,ps -> {
            ps.setString(1, se.getEccpubkey());
            ps.setString(2, se.getRsapubkey());
            ps.setString(3, se.getUpSeed());
            ps.setString(4, se.getDownSeed());
            ps.setString(5, se.getMacSeed());
            ps.setString(6, se.getEncryptedkey());
            ps.setInt(7, se.getAlgType());
            ps.setString(8, se.getMasterKeyName());
            ps.setInt(9, se.getDownCounter());
            ps.setString(10, se.getHid());
        });
    }
}