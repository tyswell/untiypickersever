package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.RFacebookGroup;

public class RFacebookGroupDAO extends BaseDAO<RFacebookGroup> {

  private static final String FIELD_NAMES = "code, groupname, groupid, web_type_code, active";

  protected static final RowMapper<RFacebookGroup> ROW_MAPPER = new RowMapper<RFacebookGroup>() {
    public RFacebookGroup mapRow(ResultSet rs, int index) throws SQLException {
      RFacebookGroup result = new RFacebookGroup();

      result.setCode( (Integer) rs.getObject("code") );
      result.setGroupname( rs.getString("groupname") );
      result.setGroupid( rs.getString("groupid") );
      result.setWebTypeCode( (Integer) rs.getObject("web_type_code") );
      result.setActive( rs.getString("active") );

      return result;
    }
  };

  public void insert(final RFacebookGroup rFacebookGroup) {
    getJdbcTemplate().update(
        "insert into r_facebook_group (" + FIELD_NAMES + ") values(?, ?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, rFacebookGroup.getCode());
            pstmt.setString(2, rFacebookGroup.getGroupname());
            pstmt.setString(3, rFacebookGroup.getGroupid());
            pstmt.setObject(4, rFacebookGroup.getWebTypeCode());
            pstmt.setString(5, rFacebookGroup.getActive());
          }
        });
  }

  public boolean isKeyExist(final Integer code) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from r_facebook_group where code = ?",
        new Object[] {
            code
        }, Integer.class));
  }

  public RFacebookGroup selectByKey(final Integer code) {
    List<RFacebookGroup> results = selectWithSuffix(
        "where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, code);
          }
        });

    if (results.size() != 0) {
      return (RFacebookGroup) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<RFacebookGroup> selectAll() {
    return selectWithSuffix("");
  }

  protected List<RFacebookGroup> selectWithSuffix(String suffixSql) {
    return (List<RFacebookGroup>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_facebook_group " + suffixSql,
        ROW_MAPPER);
  }

  protected List<RFacebookGroup> selectWithSuffix(String suffixSql, Object... args) {
    return (List<RFacebookGroup>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_facebook_group " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<RFacebookGroup> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<RFacebookGroup>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from r_facebook_group " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final RFacebookGroup rFacebookGroup) {
    getJdbcTemplate().update(
        "update r_facebook_group set groupname = ?, groupid = ?, web_type_code = ?, active = ? where code = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, rFacebookGroup.getGroupname());
            ps.setString(2, rFacebookGroup.getGroupid());
            ps.setObject(3, rFacebookGroup.getWebTypeCode());
            ps.setString(4, rFacebookGroup.getActive());
            ps.setObject(5, rFacebookGroup.getCode());
          }
        });
  }

  public void deleteByKey(final Integer code) {
    getJdbcTemplate().update(
        "delete from r_facebook_group where code = ?",
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
