package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.EUser;

public class EUserDAO extends BaseDAO<EUser> {

  private static final String FIELD_NAMES = "user_id, email, display_name, create_date, user_login_type, login, active";

  protected static final RowMapper<EUser> ROW_MAPPER = new RowMapper<EUser>() {
    public EUser mapRow(ResultSet rs, int index) throws SQLException {
      EUser result = new EUser();

      result.setUserId( rs.getString("user_id") );
      result.setEmail( rs.getString("email") );
      result.setDisplayName( rs.getString("display_name") );
      result.setCreateDate( rs.getTimestamp("create_date") );
      result.setUserLoginType( (Integer) rs.getObject("user_login_type") );
      result.setLogin( rs.getString("login") );
      result.setActive( rs.getString("active") );

      return result;
    }
  };

  public void insert(final EUser eUser) {
    getJdbcTemplate().update(
        "insert into e_user (" + FIELD_NAMES + ") values(?, ?, ?, ?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setString(1, eUser.getUserId());
            pstmt.setString(2, eUser.getEmail());
            pstmt.setString(3, eUser.getDisplayName());
            pstmt.setTimestamp(4, eUser.getCreateDate());
            pstmt.setObject(5, eUser.getUserLoginType());
            pstmt.setString(6, eUser.getLogin());
            pstmt.setString(7, eUser.getActive());
          }
        });
  }

  public boolean isKeyExist(final String user_id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_user where user_id = ?",
        new Object[] {
            user_id
        }, Integer.class));
  }

  public EUser selectByKey(final String user_id) {
    List<EUser> results = selectWithSuffix(
        "where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, user_id);
          }
        });

    if (results.size() != 0) {
      return (EUser) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<EUser> selectAll() {
    return selectWithSuffix("");
  }

  protected List<EUser> selectWithSuffix(String suffixSql) {
    return (List<EUser>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user " + suffixSql,
        ROW_MAPPER);
  }

  protected List<EUser> selectWithSuffix(String suffixSql, Object... args) {
    return (List<EUser>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<EUser> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<EUser>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final EUser eUser) {
    getJdbcTemplate().update(
        "update e_user set email = ?, display_name = ?, create_date = ?, user_login_type = ?, login = ?, active = ? where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, eUser.getEmail());
            ps.setString(2, eUser.getDisplayName());
            ps.setTimestamp(3, eUser.getCreateDate());
            ps.setObject(4, eUser.getUserLoginType());
            ps.setString(5, eUser.getLogin());
            ps.setString(6, eUser.getActive());
            ps.setString(7, eUser.getUserId());
          }
        });
  }

  public void deleteByKey(final String user_id) {
    getJdbcTemplate().update(
        "delete from e_user where user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, user_id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2017-01-06,   18:00:18
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */
  
  	public boolean isEmailExist(final String email) {
		return (0 != getJdbcTemplate().queryForObject(
				"select count(*) from e_user where email = ?",
				new Object[] { email }, Integer.class));
	}

	public EUser selectByEmail(final String email) {
		List<EUser> results = selectWithSuffix("where email = ?",
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setObject(1, email);
					}
				});
	
		if (results.size() != 0) {
			return (EUser) results.get(0);
		} else {
			return null;
		}
	}
	
	public int selectMaxId() {
		return getJdbcTemplate().queryForObject(
				"select max(user_id) from " + "e_user", Integer.class);
	}
	
	public void setStatusLogin(final String userId, final String status) {
	    getJdbcTemplate().update(
	        "update e_searching set login = ? where user_id = ?",
	        new PreparedStatementSetter() {
	          public void setValues(PreparedStatement ps) throws SQLException {
	            ps.setString(1, status);
	            ps.setObject(2, userId);
	          }
	        });
	 }

}
