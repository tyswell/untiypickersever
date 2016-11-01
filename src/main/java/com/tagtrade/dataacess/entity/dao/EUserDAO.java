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

	private static final String FIELD_NAMES = "user_id, email, display_name, token_uid, token_uid_expire_date, create_date, user_login_type, active";

	protected static final RowMapper<EUser> ROW_MAPPER = new RowMapper<EUser>() {
		public EUser mapRow(ResultSet rs, int index) throws SQLException {
			EUser result = new EUser();

			result.setUserId((Integer) rs.getObject("user_id"));
			result.setEmail(rs.getString("email"));
			result.setDisplayName(rs.getString("display_name"));
			result.setTokenUid(rs.getString("token_uid"));
			result.setTokenUidExpireDate(rs.getDate("token_uid_expire_date"));
			result.setCreateDate(rs.getTimestamp("create_date"));
			result.setUserLoginType((Integer) rs.getObject("user_login_type"));
			result.setActive(rs.getString("active"));

			return result;
		}
	};

	public void insert(final EUser eUser) {
		getJdbcTemplate().update(
				"insert into e_user (" + FIELD_NAMES
						+ ") values(?, ?, ?, ?, ?, ?, ?, ?)",
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement pstmt)
							throws SQLException {
						pstmt.setObject(1, eUser.getUserId());
						pstmt.setString(2, eUser.getEmail());
						pstmt.setString(3, eUser.getDisplayName());
						pstmt.setString(4, eUser.getTokenUid());
						pstmt.setDate(5, eUser.getTokenUidExpireDate());
						pstmt.setTimestamp(6, eUser.getCreateDate());
						pstmt.setObject(7, eUser.getUserLoginType());
						pstmt.setString(8, eUser.getActive());
					}
				});
	}

	public boolean isKeyExist(final Integer user_id) {
		return (0 != getJdbcTemplate().queryForObject(
				"select count(*) from e_user where user_id = ?",
				new Object[] { user_id }, Integer.class));
	}

	public EUser selectByKey(final Integer user_id) {
		List<EUser> results = selectWithSuffix("where user_id = ?",
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setObject(1, user_id);
					}
				});

		if (results.size() != 0) {
			return (EUser) results.get(0);
		} else {
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
				"select " + FIELD_NAMES + " from e_user " + suffixSql, args,
				ROW_MAPPER);
	}

	protected List<EUser> selectWithSuffix(String suffixSql,
			PreparedStatementSetter pss) {
		return (List<EUser>) getJdbcTemplate().query(
				"select " + FIELD_NAMES + " from e_user " + suffixSql, pss,
				ROW_MAPPER);
	}

	public void updateByKey(final EUser eUser) {
		getJdbcTemplate()
				.update("update e_user set email = ?, display_name = ?, token_uid = ?, token_uid_expire_date = ?, create_date = ?, user_login_type = ?, active = ? where user_id = ?",
						new PreparedStatementSetter() {
							public void setValues(PreparedStatement ps)
									throws SQLException {
								ps.setString(1, eUser.getEmail());
								ps.setString(2, eUser.getDisplayName());
								ps.setString(3, eUser.getTokenUid());
								ps.setDate(4, eUser.getTokenUidExpireDate());
								ps.setTimestamp(5, eUser.getCreateDate());
								ps.setObject(6, eUser.getUserLoginType());
								ps.setString(7, eUser.getActive());
								ps.setObject(8, eUser.getUserId());
							}
						});
	}

	public void deleteByKey(final Integer user_id) {
		getJdbcTemplate().update("delete from e_user where user_id = ?",
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setObject(1, user_id);
					}
				});
	}

	/**
	 ******************************************************************************* 
	 * Code Generated on 2016-11-01, 15:22:12
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

}
