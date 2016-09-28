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

  private static final String FIELD_NAMES = "user_id, create_date, user_login_type, active";

  protected static final RowMapper<EUser> ROW_MAPPER = new RowMapper<EUser>() {
    public EUser mapRow(ResultSet rs, int index) throws SQLException {
      EUser result = new EUser();

      result.setUserId( (Integer) rs.getObject("user_id") );
      result.setCreateDate( rs.getTimestamp("create_date") );
      result.setUserLoginType( (Integer) rs.getObject("user_login_type") );
      result.setActive( rs.getString("active") );

      return result;
    }
  };

  public void insert(final EUser eUser) {
	  getJdbcTemplate().update(
        "insert into e_user (" + FIELD_NAMES + ") values(?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, eUser.getUserId());
            pstmt.setTimestamp(2, eUser.getCreateDate());
            pstmt.setObject(3, eUser.getUserLoginType());
            pstmt.setString(4, eUser.getActive());
          }
        });
  }

  public boolean isKeyExist(final Integer user_id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_user where user_id = ?",
        new Object[] {
            user_id
        }, Integer.class));
  }

  public EUser selectByKey(final Integer user_id) {
    List<EUser> results = selectWithSuffix(
        "where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, user_id);
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
        "update e_user set create_date = ?, user_login_type = ?, active = ? where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setTimestamp(1, eUser.getCreateDate());
            ps.setObject(2, eUser.getUserLoginType());
            ps.setString(3, eUser.getActive());
            ps.setObject(4, eUser.getUserId());
          }
        });
  }

  public void deleteByKey(final Integer user_id) {
    getJdbcTemplate().update(
        "delete from e_user where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, user_id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-08-09,   19:25:50
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
