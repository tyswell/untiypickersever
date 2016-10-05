package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.CriteriaBuilder;
import com.tagtrade.dataacess.entity.bean.ErSearchTypeMapWebType;


public class ErSearchTypeMapWebTypeDAO extends BaseDAO<ErSearchTypeMapWebType> {

  private static final String FIELD_NAMES = "search_type_code, web_type_code";

  protected static final RowMapper<ErSearchTypeMapWebType> ROW_MAPPER = new RowMapper<ErSearchTypeMapWebType>() {
    public ErSearchTypeMapWebType mapRow(ResultSet rs, int index) throws SQLException {
      ErSearchTypeMapWebType result = new ErSearchTypeMapWebType();

      result.setSearchTypeCode( (Integer) rs.getObject("search_type_code") );
      result.setWebTypeCode( (Integer) rs.getObject("web_type_code") );

      return result;
    }
  };

  public void insert(final ErSearchTypeMapWebType erSearchTypeMapWebType) {
    getJdbcTemplate().update(
        "insert into er_search_type_map_web_type (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, erSearchTypeMapWebType.getSearchTypeCode());
            pstmt.setObject(2, erSearchTypeMapWebType.getWebTypeCode());
          }
        });
  }

  public boolean isKeyExist(final Integer search_type_code, final Integer web_type_code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from er_search_type_map_web_type where search_type_code = ? and web_type_code = ?",
        new Object[] { search_type_code, web_type_code }, Integer.class));
  }

  public ErSearchTypeMapWebType selectByKey(final Integer search_type_code, final Integer web_type_code) {
    List<ErSearchTypeMapWebType> results = selectWithSuffix(
        "where search_type_code = ? and web_type_code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, search_type_code);
            ps.setObject(2, web_type_code);
          }
        });

    if (results.size() != 0) {
      return (ErSearchTypeMapWebType) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<ErSearchTypeMapWebType> selectAll() {
    return selectWithSuffix("");
  }

  protected List<ErSearchTypeMapWebType> selectWithSuffix(String suffixSql) {
    return (List<ErSearchTypeMapWebType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_search_type_map_web_type " + suffixSql,
        ROW_MAPPER);
  }

  protected List<ErSearchTypeMapWebType> selectWithSuffix(String suffixSql, Object... args) {
    return (List<ErSearchTypeMapWebType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_search_type_map_web_type " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<ErSearchTypeMapWebType> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<ErSearchTypeMapWebType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_search_type_map_web_type " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void deleteByKey(final Integer search_type_code, final Integer web_type_code) {
    getJdbcTemplate().update(
        "delete from er_search_type_map_web_type where search_type_code = ? and web_type_code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, search_type_code);
            ps.setObject(2, web_type_code);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-03,   14:00:09
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */
  
  public List<ErSearchTypeMapWebType> selectSearchType(int webTypeCode) {
	  CriteriaBuilder cb = new CriteriaBuilder();
	  cb.and("web_type_code", webTypeCode);
	  return selectWithSuffix(cb);
  }

}
