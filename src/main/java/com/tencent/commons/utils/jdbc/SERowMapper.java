/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.utils.jdbc;

import com.tencent.iot.domain.SE;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bobzbfeng
 */
public class SERowMapper implements RowMapper<SE>{

    @Override
    public SE mapRow(ResultSet rs, int rowNum) throws SQLException {
        SE se = new SE();

        se.setHid(rs.getString("c_hid"));
        se.setDownSeed(rs.getString("c_downseed"));
        se.setUpSeed(rs.getString("c_upseed"));
        se.setMacSeed(rs.getString("c_macseed"));
        se.setRsapubkey(rs.getString("c_rsapubkey"));
        se.setEccpubkey(rs.getString("c_eccpubkey"));
        se.setEncryptedkey(rs.getString("e_encryptedkey"));
        //查询时计数器默认加1
        se.setDownCounter(rs.getInt("c_downcounter")+1);
        se.setAlgType(rs.getInt("c_algtype"));

        se.setAsymmetricType (se.getAlgType() & 0x01);
        se.setSymmetricType((se.getAlgType() & 0x06) >> 1);

        se.setMasterKeyName(rs.getString("c_master_key_name"));

        return se;
    }
}