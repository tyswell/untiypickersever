package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RExtractType;

public class RExtractTypeDAO extends BaseDAO<RExtractType> {

  private static final String FIELD_NAMES = "code, description";

  protected static final RowMapper<RExtractType> ROW_MAPPER = new RowMapper<RExtractType>() {
    public RExtractType mapRow(ResultSet rs, int index) throws SQLException {
      RExtractType result = new RExtractType();

      result.setCode( (Integer) rs.getObject("code") );
      result.setDescription( rs.getString("description") );

      return result;
    }
  };

  public void insert(final RExtractType rExtractType) {
    getJdbcTemplate().update(
        "insert into r_extract_type (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rExtractType.getCode());
            pstmt.setString(2, rExtractType.getDescription());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_extract_type where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RExtractType selectByKey(final Integer code) {
    List<RExtractType> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RExtractType) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RExtractType> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RExtractType> selectWithSuffix(String suffixSql) {
    return (List<RExtractType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_extract_type " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RExtractType> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RExtractType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_extract_type " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RExtractType> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RExtractType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_extract_type " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RExtractType rExtractType) {
    getJdbcTemplate().update(
        "update r_extract_type set description = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, rExtractType.getDescription());
            ps.setObject(2, rExtractType.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_extract_type where code = ?",
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
