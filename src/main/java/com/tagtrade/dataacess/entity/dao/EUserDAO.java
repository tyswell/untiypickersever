package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.EUser;

public class EUserDAO extends BaseDAO<EUser> {

  private static final String FIELD_NAMES = "username, display_name, token_uid, token_uid_expire_date, create_date, user_login_type, active";

  protected static final RowMapper<EUser> ROW_MAPPER = new RowMapper<EUser>() {
    public EUser mapRow(ResultSet rs, int index) throws SQLException {
      EUser result = new EUser();

      result.setUsername( rs.getString("username") );
      result.setDisplayName( rs.getString("display_name") );
      result.setTokenUid( rs.getString("token_uid") );
      result.setTokenUidExpireDate( rs.getDate("token_uid_expire_date") );
      result.setCreateDate( rs.getTimestamp("create_date") );
      result.setUserLoginType( (Integer) rs.getObject("user_login_type") );
      result.setActive( rs.getString("active") );

      return result;
    }
  };

  public void insert(final EUser eUser) {
    getJdbcTemplate().update(
        "insert into e_user (" + FIELD_NAMES + ") values(?, ?, ?, ?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setString(1, eUser.getUsername());
            pstmt.setString(2, eUser.getDisplayName());
            pstmt.setString(3, eUser.getTokenUid());
            pstmt.setDate(4, eUser.getTokenUidExpireDate());
            pstmt.setTimestamp(5, eUser.getCreateDate());
            pstmt.setObject(6, eUser.getUserLoginType());
            pstmt.setString(7, eUser.getActive());
          }
        });
  }

  public boolean isKeyExist(final String username) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_user where username = ?",
        new Object[] {
            username
        }, Integer.class));
  }

  public EUser selectByKey(final String username) {
    List<EUser> results = selectWithSuffix(
        "where username = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, username);
          }
        });

    if (results.size() != 0) {
      return (EUser) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<EUser> selectAll() {
    return selectWithSuffix("");
  }

  protected List<EUser> selectWithSuffix(String suffixSql) {
    return (List<EUser>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user " + suffixSql,
        ROW_MAPPER);
  }

  protected List<EUser> selectWithSuffix(String suffixSql, Object... args) {
    return (List<EUser>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<EUser> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<EUser>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final EUser eUser) {
    getJdbcTemplate().update(
        "update e_user set display_name = ?, token_uid = ?, token_uid_expire_date = ?, create_date = ?, user_login_type = ?, active = ? where username = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, eUser.getDisplayName());
            ps.setString(2, eUser.getTokenUid());
            ps.setDate(3, eUser.getTokenUidExpireDate());
            ps.setTimestamp(4, eUser.getCreateDate());
            ps.setObject(5, eUser.getUserLoginType());
            ps.setString(6, eUser.getActive());
            ps.setString(7, eUser.getUsername());
          }
        });
  }

  public void deleteByKey(final String username) {
    getJdbcTemplate().update(
        "delete from e_user where username = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, username);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-27,   16:46:16
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
