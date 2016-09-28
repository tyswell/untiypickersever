package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RUserLoginType;

public class RUserLoginTypeDAO extends BaseDAO<RUserLoginType> {

  private static final String FIELD_NAMES = "code, description";

  protected static final RowMapper<RUserLoginType> ROW_MAPPER = new RowMapper<RUserLoginType>() {
    public RUserLoginType mapRow(ResultSet rs, int index) throws SQLException {
      RUserLoginType result = new RUserLoginType();

      result.setCode( (Integer) rs.getObject("code") );
      result.setDescription( rs.getString("description") );

      return result;
    }
  };

  public void insert(final RUserLoginType rUserLoginType) {
    getJdbcTemplate().update(
        "insert into r_user_login_type (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rUserLoginType.getCode());
            pstmt.setString(2, rUserLoginType.getDescription());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_user_login_type where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RUserLoginType selectByKey(final Integer code) {
    List<RUserLoginType> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RUserLoginType) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RUserLoginType> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RUserLoginType> selectWithSuffix(String suffixSql) {
    return (List<RUserLoginType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_user_login_type " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RUserLoginType> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RUserLoginType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_user_login_type " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RUserLoginType> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RUserLoginType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_user_login_type " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RUserLoginType rUserLoginType) {
    getJdbcTemplate().update(
        "update r_user_login_type set description = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, rUserLoginType.getDescription());
            ps.setObject(2, rUserLoginType.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_user_login_type where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
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
