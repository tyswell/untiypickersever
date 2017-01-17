package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RFacebookGroupType;

public class RFacebookGroupTypeDAO extends BaseDAO<RFacebookGroupType> {

  private static final String FIELD_NAMES = "code, description";

  protected static final RowMapper<RFacebookGroupType> ROW_MAPPER = new RowMapper<RFacebookGroupType>() {
    public RFacebookGroupType mapRow(ResultSet rs, int index) throws SQLException {
      RFacebookGroupType result = new RFacebookGroupType();

      result.setCode( (Integer) rs.getObject("code") );
      result.setDescription( rs.getString("description") );

      return result;
    }
  };

  public void insert(final RFacebookGroupType rFacebookGroupType) {
    getJdbcTemplate().update(
        "insert into r_facebook_group_type (" + FIELD_NAMES + ") values(?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rFacebookGroupType.getCode());
            pstmt.setString(2, rFacebookGroupType.getDescription());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_facebook_group_type where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RFacebookGroupType selectByKey(final Integer code) {
    List<RFacebookGroupType> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RFacebookGroupType) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RFacebookGroupType> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RFacebookGroupType> selectWithSuffix(String suffixSql) {
    return (List<RFacebookGroupType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_facebook_group_type " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RFacebookGroupType> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RFacebookGroupType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_facebook_group_type " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RFacebookGroupType> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RFacebookGroupType>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_facebook_group_type " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RFacebookGroupType rFacebookGroupType) {
    getJdbcTemplate().update(
        "update r_facebook_group_type set description = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, rFacebookGroupType.getDescription());
            ps.setObject(2, rFacebookGroupType.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_facebook_group_type where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2017-01-17,   19:05:44
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
