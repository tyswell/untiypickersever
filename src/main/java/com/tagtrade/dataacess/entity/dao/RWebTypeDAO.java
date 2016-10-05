package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RWebType;

public class RWebTypeDAO extends BaseDAO<RWebType> {

  private static final String FIELD_NAMES = "code, description";

  protected static final RowMapper<RWebType> ROW_MAPPER = new RowMapper<RWebType>() {
    public RWebType mapRow(ResultSet rs, int index) throws SQLException {
      RWebType result = new RWebType();

      result.setCode( (Integer) rs.getObject("code") );
      result.setDescription( rs.getString("description") );

      return result;
    }
  };

  public void insert(final RWebType rWebType) {
    getJdbcTemplate().update(
        "insert into r_web_type (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rWebType.getCode());
            pstmt.setString(2, rWebType.getDescription());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_web_type where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RWebType selectByKey(final Integer code) {
    List<RWebType> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RWebType) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RWebType> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RWebType> selectWithSuffix(String suffixSql) {
    return (List<RWebType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_web_type " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RWebType> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RWebType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_web_type " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RWebType> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RWebType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_web_type " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RWebType rWebType) {
    getJdbcTemplate().update(
        "update r_web_type set description = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, rWebType.getDescription());
            ps.setObject(2, rWebType.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_web_type where code = ?",
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
