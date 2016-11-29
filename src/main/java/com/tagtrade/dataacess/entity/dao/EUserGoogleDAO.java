package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.EUserGoogle;

public class EUserGoogleDAO extends BaseDAO<EUserGoogle> {

  private static final String FIELD_NAMES = "user_id, google_id, token_google, modify_date";

  protected static final RowMapper<EUserGoogle> ROW_MAPPER = new RowMapper<EUserGoogle>() {
    public EUserGoogle mapRow(ResultSet rs, int index) throws SQLException {
      EUserGoogle result = new EUserGoogle();

      result.setUserId( rs.getString("user_id") );
      result.setGoogleId( rs.getString("google_id") );
      result.setTokenGoogle( rs.getString("token_google") );
      result.setModifyDate( rs.getTimestamp("modify_date") );

      return result;
    }
  };

  public void insert(final EUserGoogle eUserGoogle) {
    getJdbcTemplate().update(
        "insert into e_user_google (" + FIELD_NAMES + ") values(?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setString(1, eUserGoogle.getUserId());
            pstmt.setString(2, eUserGoogle.getGoogleId());
            pstmt.setString(3, eUserGoogle.getTokenGoogle());
            pstmt.setTimestamp(4, eUserGoogle.getModifyDate());
          }
        });
  }

  public boolean isKeyExist(final String user_id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_user_google where user_id = ?",
        new Object[] {
            user_id
        }, Integer.class));
  }

  public EUserGoogle selectByKey(final String user_id) {
    List<EUserGoogle> results = selectWithSuffix(
        "where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, user_id);
          }
        });

    if (results.size() != 0) {
      return (EUserGoogle) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<EUserGoogle> selectAll() {
    return selectWithSuffix("");
  }

  protected List<EUserGoogle> selectWithSuffix(String suffixSql) {
    return (List<EUserGoogle>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_google " + suffixSql,
        ROW_MAPPER);
  }

  protected List<EUserGoogle> selectWithSuffix(String suffixSql, Object... args) {
    return (List<EUserGoogle>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_google " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<EUserGoogle> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<EUserGoogle>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_google " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final EUserGoogle eUserGoogle) {
    getJdbcTemplate().update(
        "update e_user_google set google_id = ?, token_google = ?, modify_date = ? where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, eUserGoogle.getGoogleId());
            ps.setString(2, eUserGoogle.getTokenGoogle());
            ps.setTimestamp(3, eUserGoogle.getModifyDate());
            ps.setString(4, eUserGoogle.getUserId());
          }
        });
  }

  public void deleteByKey(final String user_id) {
    getJdbcTemplate().update(
        "delete from e_user_google where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, user_id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-11-28,   18:31:59
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
