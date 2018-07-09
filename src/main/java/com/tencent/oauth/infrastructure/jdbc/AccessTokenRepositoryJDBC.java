package com.tencent.oauth.infrastructure.jdbc;

import com.tencent.commons.utils.jdbc.AccessTokenRowMapper;
import com.tencent.oauth.domain.oauth.AccessToken;
import com.tencent.oauth.domain.oauth.AccessTokenRepository;
import com.tencent.oauth.domain.oauth.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author bobzbfeng
 */
@Repository("accessTokenRepositoryJDBC")
public class AccessTokenRepositoryJDBC implements AccessTokenRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private AccessTokenRowMapper rowMapper = new AccessTokenRowMapper();

    @Override
    public void saveAccessToken(AccessToken accessToken) {
        //this.mongoTemplate().save(accessToken);
        long countAccessToken = this.countAccessToken(accessToken.tokenId());
        StringBuffer sql = null;
        if (countAccessToken > 0) {
            sql = new StringBuffer("update t_oauth_access_token set c_create_time=?,c_token=?,c_authentication_id=?,c_authentication=?,c_user_name=?,c_client_id=?,c_refresh_token=? "
                    + "where c_token_id='"+accessToken.tokenId()+"'");
        } else {
            sql = new StringBuffer("insert into t_oauth_access_token(c_token_id,c_create_time,c_token,c_authentication_id,c_authentication,c_user_name,c_client_id,c_refresh_token) values("
                    + "'"+accessToken.tokenId()+"',?,?,?,?,?,?,?)");
        }
        System.out.println(accessToken.tokenId());
        jdbcTemplate.update(sql.toString(),ps -> {

                ps.setTimestamp(1, new Timestamp(accessToken.getRealCreateTime().getTime()));
                ps.setBytes(2, accessToken.getToken());
                ps.setString(3, accessToken.authenticationId());
                ps.setBytes( 4, accessToken.getAuthentication());
                ps.setString(5, accessToken.username());
                ps.setString(6, accessToken.clientId());
                ps.setString(7, accessToken.refreshToken());
        });
    }

    public long countAccessToken(String tokenId) {
        StringBuffer sql = new StringBuffer("select count(c_token_id) from t_oauth_access_token where c_token_id='"+tokenId+"'");
        return jdbcTemplate.queryForObject(sql.toString(), Long.class);
    }

    @Override
    public AccessToken findAccessToken(String tokenId) {
        StringBuffer sql = new StringBuffer("select * from t_oauth_access_token where c_token_id='"+tokenId+"'");
        List<AccessToken> tokens = jdbcTemplate.query(sql.toString(), new Object[]{}, rowMapper);
        if(tokens.isEmpty()){
            return null;
        }
        return tokens.get(0);
    }

    @Override
    public void removeAccessToken(String tokenId) {
        StringBuffer sql = new StringBuffer("delete from t_oauth_access_token where c_token_id='"+tokenId+"'");
        this.jdbcTemplate.execute(sql.toString());
    }

    @Override
    public void saveRefreshToken(RefreshToken refreshToken) {
        long countRefreshToken = this.countRefreshToken(refreshToken.tokenId());
        StringBuffer sql = null;
        if (countRefreshToken > 0) {
            sql = new StringBuffer(
                    "update t_oauth_refresh_token set c_create_time=?,c_token=?,c_authentication=? where c_token_id='"
                            + refreshToken.tokenId() + "'");

        } else {
            sql = new StringBuffer("insert into t_oauth_refresh_token(c_token_id,c_create_time,c_token,c_authentication) values("
                    + "'"+refreshToken.tokenId()+"',?,?,?)");
        }
        jdbcTemplate.update(sql.toString(), ps -> {
            ps.setTimestamp(1, new Timestamp(refreshToken.getRealCreateTime().getTime()));
            ps.setBytes(2, refreshToken.getToken());
            ps.setBytes(3, refreshToken.getAuthentication());
        });
    }

    private long countRefreshToken(String tokenId) {
        StringBuffer sql = new StringBuffer("select count(c_token_id) from t_oauth_refresh_token where c_token_id='"+tokenId+"'");
        return jdbcTemplate.queryForObject(sql.toString(), Long.class);
    }

    @Override
    public RefreshToken findRefreshToken(String tokenId) {
        StringBuffer sql = new StringBuffer("select c_create_time as createTime,c_token as token,"
                + "c_authentication as authentication from t_oauth_access_token where c_token_id='"+tokenId+"'");
        try {
            return jdbcTemplate.queryForObject(sql.toString(), new RowMapper<RefreshToken>() {

                @Override
                public RefreshToken mapRow(ResultSet rs, int rowNum) throws SQLException {
                    RefreshToken refreshToken = new RefreshToken();
                    refreshToken.tokenId(tokenId);
                    refreshToken.setCreateTime(rs.getTimestamp("createTime"));
                    refreshToken.setToken(rs.getBytes("token"));
                    refreshToken.setAuthentication(rs.getBytes("authentication"));
                    return refreshToken;
                }

            });
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeRefreshToken(String tokenId) {
        StringBuffer sql = new StringBuffer("delete from t_oauth_refresh_token where c_token_id='"+tokenId+"'");
        jdbcTemplate.execute(sql.toString());
    }

    @Override
    public void removeAccessTokenByRefreshToken(String refreshToken) {
        StringBuffer sql = new StringBuffer("delete from t_oauth_access_token where c_refresh_token='"+refreshToken+"'");
        jdbcTemplate.execute(sql.toString());
    }

    @Override
    public AccessToken findAccessTokenByRefreshToken(String refreshToken) {
        StringBuffer sql = new StringBuffer("select * from t_oauth_access_token where c_refresh_token='"+refreshToken+"'");
        List<AccessToken> accessTokens =  jdbcTemplate.query(sql.toString(), new Object[]{}, rowMapper);
        return accessTokens.isEmpty()?null:accessTokens.get(0);
    }

    @Override
    public AccessToken findAccessTokenByAuthenticationId(String authenticationId) {
        StringBuffer sql = new StringBuffer("select * from t_oauth_access_token where c_authentication_id='"+authenticationId+"'");
        List<AccessToken> accessTokens =  jdbcTemplate.query(sql.toString(), new Object[]{}, rowMapper);
        return accessTokens.isEmpty()?null:accessTokens.get(0);
    }

    @Override
    public List<AccessToken> findAccessTokenByUsername(String username) {
        StringBuffer sql = new StringBuffer("select * from t_oauth_access_token where c_user_name='"+username+"'");
        return jdbcTemplate.query(sql.toString(), new Object[]{}, rowMapper);
    }

    @Override
    public List<AccessToken> findAccessTokenByClientId(String clientId) {
        StringBuffer sql = new StringBuffer("select * from t_oauth_access_token where c_client_id='"+clientId+"'");
        return jdbcTemplate.query(sql.toString(), new Object[]{}, rowMapper);
    }

    @Override
    public List<AccessToken> findAccessTokenByClientIdAndUsername(String clientId, String username) {
        StringBuffer sql = new StringBuffer("select * from t_oauth_access_token where c_client_id= ? and c_user_name = ?");
        return jdbcTemplate.query(sql.toString(), new Object[]{clientId,username}, rowMapper);
    }
}
