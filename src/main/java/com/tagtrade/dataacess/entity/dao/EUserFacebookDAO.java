package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.EUserFacebook;

public class EUserFacebookDAO extends BaseDAO<EUserFacebook> {

  private static final String FIELD_NAMES = "username, token_facebook, modify_date";

  protected static final RowMapper<EUserFacebook> ROW_MAPPER = new RowMapper<EUserFacebook>() {
    public EUserFacebook mapRow(ResultSet rs, int index) throws SQLException {
      EUserFacebook result = new EUserFacebook();

      result.setUsername( rs.getString("username") );
      result.setTokenFacebook( rs.getString("token_facebook") );
      result.setModifyDate( rs.getTimestamp("modify_date") );

      return result;
    }
  };

  public void insert(final EUserFacebook eUserFacebook) {
    getJdbcTemplate().update(
        "insert into e_user_facebook (" + FIELD_NAMES + ") values(?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setString(1, eUserFacebook.getUsername());
            pstmt.setString(2, eUserFacebook.getTokenFacebook());
            pstmt.setTimestamp(3, eUserFacebook.getModifyDate());
          }
        });
  }

  public boolean isKeyExist(final String username) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_user_facebook where username = ?",
        new Object[] {
            username
        }, Integer.class));
  }

  public EUserFacebook selectByKey(final String username) {
    List<EUserFacebook> results = selectWithSuffix(
        "where username = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, username);
          }
        });

    if (results.size() != 0) {
      return (EUserFacebook) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<EUserFacebook> selectAll() {
    return selectWithSuffix("");
  }

  protected List<EUserFacebook> selectWithSuffix(String suffixSql) {
    return (List<EUserFacebook>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_facebook " + suffixSql,
        ROW_MAPPER);
  }

  protected List<EUserFacebook> selectWithSuffix(String suffixSql, Object... args) {
    return (List<EUserFacebook>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_facebook " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<EUserFacebook> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<EUserFacebook>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_facebook " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final EUserFacebook eUserFacebook) {
    getJdbcTemplate().update(
        "update e_user_facebook set token_facebook = ?, modify_date = ? where username = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, eUserFacebook.getTokenFacebook());
            ps.setTimestamp(2, eUserFacebook.getModifyDate());
            ps.setString(3, eUserFacebook.getUsername());
          }
        });
  }

  public void deleteByKey(final String username) {
    getJdbcTemplate().update(
        "delete from e_user_facebook where username = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, username);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-12,   14:09:35
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
