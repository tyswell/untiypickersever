package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.ErUserRole;


public class ErUserRoleDAO extends BaseDAO<ErUserRole> {

  private static final String FIELD_NAMES = "user_id, role_code";

  protected static final RowMapper<ErUserRole> ROW_MAPPER = new RowMapper<ErUserRole>() {
    public ErUserRole mapRow(ResultSet rs, int index) throws SQLException {
      ErUserRole result = new ErUserRole();

      result.setUserId( (Integer) rs.getObject("user_id") );
      result.setRoleCode( (Integer) rs.getObject("role_code") );

      return result;
    }
  };

  public void insert(final ErUserRole erUserRole) {
	  getJdbcTemplate().update(
        "insert into er_user_role (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, erUserRole.getUserId());
            pstmt.setObject(2, erUserRole.getRoleCode());
          }
        });
  }

  public boolean isKeyExist(final Integer user_id, final Integer role_code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from er_user_role where user_id = ? and role_code = ?",
        new Object[] {
            user_id, role_code
        }, Integer.class));
  }

  public ErUserRole selectByKey(final Integer user_id, final Integer role_code) {
    List<ErUserRole> results = selectWithSuffix(
        "where user_id = ? and role_code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, user_id);
            ps.setObject(2, role_code);
          }
        });

    if (results.size() != 0) {
      return (ErUserRole) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<ErUserRole> selectAll() {
    return selectWithSuffix("");
  }

  protected List<ErUserRole> selectWithSuffix(String suffixSql) {
    return (List<ErUserRole>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_user_role " + suffixSql,
        ROW_MAPPER);
  }

  protected List<ErUserRole> selectWithSuffix(String suffixSql, Object... args) {
    return (List<ErUserRole>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_user_role " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<ErUserRole> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<ErUserRole>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_user_role " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void deleteByKey(final Integer user_id, final Integer role_code) {
	  getJdbcTemplate().update(
        "delete from er_user_role where user_id = ? and role_code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, user_id);
            ps.setObject(2, role_code);
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
