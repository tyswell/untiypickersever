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

  private static final String FIELD_NAMES = "facebook_id, user_id, token, expire_date, modify_date";

  protected static final RowMapper<EUserFacebook> ROW_MAPPER = new RowMapper<EUserFacebook>() {
    public EUserFacebook mapRow(ResultSet rs, int index) throws SQLException {
      EUserFacebook result = new EUserFacebook();

      result.setFacebookId( rs.getString("facebook_id") );
      result.setUserId( (Integer) rs.getObject("user_id") );
      result.setToken( rs.getString("token") );
      result.setExpireDate( rs.getDate("expire_date") );
      result.setModifyDate( rs.getTimestamp("modify_date") );

      return result;
    }
  };

  public void insert(final EUserFacebook eUserFacebook) {
    getJdbcTemplate().update(
        "insert into e_user_facebook (" + FIELD_NAMES + ") values(?, ?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setString(1, eUserFacebook.getFacebookId());
            pstmt.setObject(2, eUserFacebook.getUserId());
            pstmt.setString(3, eUserFacebook.getToken());
            pstmt.setDate(4, eUserFacebook.getExpireDate());
            pstmt.setTimestamp(5, eUserFacebook.getModifyDate());
          }
        });
  }

  public boolean isKeyExist(final String facebook_id, final Integer user_id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_user_facebook where facebook_id = ? and user_id = ?",
        new Object[] {
            facebook_id, user_id
        }, Integer.class));
  }

  public EUserFacebook selectByKey(final String facebook_id, final Integer user_id) {
    List<EUserFacebook> results = selectWithSuffix(
        "where facebook_id = ? and user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, facebook_id);
            ps.setObject(2, user_id);
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
        "update e_user_facebook set token = ?, expire_date = ?, modify_date = ? where facebook_id = ? and user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, eUserFacebook.getToken());
            ps.setDate(2, eUserFacebook.getExpireDate());
            ps.setTimestamp(3, eUserFacebook.getModifyDate());
            ps.setString(4, eUserFacebook.getFacebookId());
            ps.setObject(5, eUserFacebook.getUserId());
          }
        });
  }

  public void deleteByKey(final String facebook_id, final Integer user_id) {
    getJdbcTemplate().update(
        "delete from e_user_facebook where facebook_id = ? and user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, facebook_id);
            ps.setObject(2, user_id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-08-09,   19:25:50
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
