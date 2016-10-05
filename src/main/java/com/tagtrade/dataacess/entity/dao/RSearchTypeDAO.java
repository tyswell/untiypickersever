package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RSearchType;

public class RSearchTypeDAO extends BaseDAO<RSearchType> {

  private static final String FIELD_NAMES = "code, description";

  protected static final RowMapper<RSearchType> ROW_MAPPER = new RowMapper<RSearchType>() {
    public RSearchType mapRow(ResultSet rs, int index) throws SQLException {
      RSearchType result = new RSearchType();

      result.setCode( (Integer) rs.getObject("code") );
      result.setDescription( rs.getString("description") );

      return result;
    }
  };

  public void insert(final RSearchType rSearchType) {
    getJdbcTemplate().update(
        "insert into r_search_type (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rSearchType.getCode());
            pstmt.setString(2, rSearchType.getDescription());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_search_type where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RSearchType selectByKey(final Integer code) {
    List<RSearchType> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RSearchType) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RSearchType> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RSearchType> selectWithSuffix(String suffixSql) {
    return (List<RSearchType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_search_type " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RSearchType> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RSearchType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_search_type " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RSearchType> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RSearchType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_search_type " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RSearchType rSearchType) {
    getJdbcTemplate().update(
        "update r_search_type set description = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, rSearchType.getDescription());
            ps.setObject(2, rSearchType.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_search_type where code = ?",
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
