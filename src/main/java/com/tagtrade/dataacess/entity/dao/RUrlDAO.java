package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RUrl;

public class RUrlDAO extends BaseDAO<RUrl> {

  private static final String FIELD_NAMES = "code, web_name_code, url, web_type_code, active";

  protected static final RowMapper<RUrl> ROW_MAPPER = new RowMapper<RUrl>() {
    public RUrl mapRow(ResultSet rs, int index) throws SQLException {
      RUrl result = new RUrl();

      result.setCode( (Integer) rs.getObject("code") );
      result.setWebNameCode( (Integer) rs.getObject("web_name_code") );
      result.setUrl( rs.getString("url") );
      result.setWebTypeCode( (Integer) rs.getObject("web_type_code") );
      result.setActive( rs.getString("active") );

      return result;
    }
  };

  public void insert(final RUrl rUrl) {
    getJdbcTemplate().update(
        "insert into r_url (" + FIELD_NAMES + ") values(?, ?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rUrl.getCode());
            pstmt.setObject(2, rUrl.getWebNameCode());
            pstmt.setString(3, rUrl.getUrl());
            pstmt.setObject(4, rUrl.getWebTypeCode());
            pstmt.setString(5, rUrl.getActive());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_url where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RUrl selectByKey(final Integer code) {
    List<RUrl> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RUrl) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RUrl> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RUrl> selectWithSuffix(String suffixSql) {
    return (List<RUrl>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_url " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RUrl> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RUrl>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_url " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RUrl> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RUrl>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_url " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RUrl rUrl) {
    getJdbcTemplate().update(
        "update r_url set web_name_code = ?, url = ?, web_type_code = ?, active = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, rUrl.getWebNameCode());
            ps.setString(2, rUrl.getUrl());
            ps.setObject(3, rUrl.getWebTypeCode());
            ps.setString(4, rUrl.getActive());
            ps.setObject(5, rUrl.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_url where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-03,   17:12:37
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
