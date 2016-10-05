package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RRole;

public class RRoleDAO extends BaseDAO<RRole> {

  private static final String FIELD_NAMES = "code, description";

  protected static final RowMapper<RRole> ROW_MAPPER = new RowMapper<RRole>() {
    public RRole mapRow(ResultSet rs, int index) throws SQLException {
      RRole result = new RRole();

      result.setCode( (Integer) rs.getObject("code") );
      result.setDescription( rs.getString("description") );

      return result;
    }
  };

  public void insert(final RRole rRole) {
    getJdbcTemplate().update(
        "insert into r_role (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rRole.getCode());
            pstmt.setString(2, rRole.getDescription());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_role where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RRole selectByKey(final Integer code) {
    List<RRole> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RRole) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RRole> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RRole> selectWithSuffix(String suffixSql) {
    return (List<RRole>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_role " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RRole> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RRole>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_role " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RRole> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RRole>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_role " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RRole rRole) {
    getJdbcTemplate().update(
        "update r_role set description = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, rRole.getDescription());
            ps.setObject(2, rRole.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_role where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-03,   16:11:19
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
