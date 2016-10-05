package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RWebName;

public class RWebNameDAO extends BaseDAO<RWebName> {

  private static final String FIELD_NAMES = "code, description";

  protected static final RowMapper<RWebName> ROW_MAPPER = new RowMapper<RWebName>() {
    public RWebName mapRow(ResultSet rs, int index) throws SQLException {
      RWebName result = new RWebName();

      result.setCode( (Integer) rs.getObject("code") );
      result.setDescription( rs.getString("description") );

      return result;
    }
  };

  public void insert(final RWebName rWebName) {
    getJdbcTemplate().update(
        "insert into r_web_name (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rWebName.getCode());
            pstmt.setString(2, rWebName.getDescription());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_web_name where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RWebName selectByKey(final Integer code) {
    List<RWebName> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RWebName) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RWebName> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RWebName> selectWithSuffix(String suffixSql) {
    return (List<RWebName>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_web_name " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RWebName> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RWebName>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_web_name " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RWebName> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RWebName>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_web_name " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RWebName rWebName) {
    getJdbcTemplate().update(
        "update r_web_name set description = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, rWebName.getDescription());
            ps.setObject(2, rWebName.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_web_name where code = ?",
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
