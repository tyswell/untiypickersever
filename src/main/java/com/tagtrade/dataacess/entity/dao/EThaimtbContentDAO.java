package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.EThaimtbContent;

public class EThaimtbContentDAO extends BaseDAO<EThaimtbContent> {

	private static final String FIELD_NAMES = "thaimtb_id, url_code, description, url_content_link, date_content_create, create_date";

	protected static final RowMapper<EThaimtbContent> ROW_MAPPER = new RowMapper<EThaimtbContent>() {
		public EThaimtbContent mapRow(ResultSet rs, int index)
				throws SQLException {
			EThaimtbContent result = new EThaimtbContent();

			result.setThaimtbId((Integer) rs.getObject("thaimtb_id"));
			result.setUrlCode((Integer) rs.getObject("url_code"));
			result.setDescription(rs.getString("description"));
			result.setUrlContentLink(rs.getString("url_content_link"));
			result.setDateContentCreate(rs.getString("date_content_create"));
			result.setCreateDate(rs.getTimestamp("create_date"));

			return result;
		}
	};

	public void insert(final EThaimtbContent eThaimtbContent) {
		getJdbcTemplate().update(
				"insert into " + "e_thaimtb_content (" + FIELD_NAMES
						+ ") values(?, ?, ?, ?, ?, ?)",
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement pstmt)
							throws SQLException {
						pstmt.setObject(1, eThaimtbContent.getThaimtbId());
						pstmt.setObject(2, eThaimtbContent.getUrlCode());
						pstmt.setString(3, eThaimtbContent.getDescription());
						pstmt.setString(4, eThaimtbContent.getUrlContentLink());
						pstmt.setString(5,
								eThaimtbContent.getDateContentCreate());
						pstmt.setTimestamp(6, eThaimtbContent.getCreateDate());
					}
				});
	}

	public boolean isKeyExist(final Integer thaimtb_id) {
		return (0 != getJdbcTemplate().queryForObject(
				"select count(*) from "
						+ "e_thaimtb_content where thaimtb_id = ?",
				new Object[] { thaimtb_id }, Integer.class));
	}

	public EThaimtbContent selectByKey(final Integer thaimtb_id) {
		List<EThaimtbContent> results = selectWithSuffix(
				"where thaimtb_id = ?", new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setObject(1, thaimtb_id);
					}
				});

		if (results.size() != 0) {
			return (EThaimtbContent) results.get(0);
		} else {
			return null;
		}
	}

	public List<EThaimtbContent> selectAll() {
		return selectWithSuffix("");
	}

	protected List<EThaimtbContent> selectWithSuffix(String suffixSql) {
		return (List<EThaimtbContent>) getJdbcTemplate().query(
				"select " + FIELD_NAMES + " from "
						+ "e_thaimtb_content " + suffixSql, ROW_MAPPER);
	}

	protected List<EThaimtbContent> selectWithSuffix(String suffixSql,
			Object... args) {
		return (List<EThaimtbContent>) getJdbcTemplate().query(
				"select " + FIELD_NAMES + " from "
						+ "e_thaimtb_content " + suffixSql, args, ROW_MAPPER);
	}

	protected List<EThaimtbContent> selectWithSuffix(String suffixSql,
			PreparedStatementSetter pss) {
		return (List<EThaimtbContent>) getJdbcTemplate().query(
				"select " + FIELD_NAMES + " from "
						+ "e_thaimtb_content " + suffixSql, pss, ROW_MAPPER);
	}

	public void updateByKey(final EThaimtbContent eThaimtbContent) {
		getJdbcTemplate()
				.update("update "
						+ "e_thaimtb_content set url_code = ?, description = ?, url_content_link = ?, date_content_create = ?, create_date = ? where thaimtb_id = ?",
						new PreparedStatementSetter() {
							public void setValues(PreparedStatement ps)
									throws SQLException {
								ps.setObject(1, eThaimtbContent.getUrlCode());
								ps.setString(2,
										eThaimtbContent.getDescription());
								ps.setString(3,
										eThaimtbContent.getUrlContentLink());
								ps.setString(4,
										eThaimtbContent.getDateContentCreate());
								ps.setTimestamp(5,
										eThaimtbContent.getCreateDate());
								ps.setObject(6, eThaimtbContent.getThaimtbId());
							}
						});
	}

	public void deleteByKey(final Integer thaimtb_id) {
		getJdbcTemplate().update(
				"delete from "
						+ "e_thaimtb_content where thaimtb_id = ?",
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setObject(1, thaimtb_id);
					}
				});
	}

	/**
	 ******************************************************************************* 
	 * Code Generated on 2016-07-13, 13:19:28
	 * 
	 * If you want to add your code, please insert it below.
	 ******************************************************************************* 
	 */

	public EThaimtbContent selectLastestId() {
		String sql = strs(
				"where thaimtb_id =",
					"(",
						"select max(thaimtb_id)",
							"from e_thaimtb_content",
					")"
		);
		List<EThaimtbContent> results = selectWithSuffix(
				sql, new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
					}
				});

		if (results.size() != 0) {
			return (EThaimtbContent) results.get(0);
		} else {
			return null;
		}
	}
	
	public int nextSequence(){
	      String sql = strs(
	        "select",
	            "max(thaimtb_id)",
	        "from" ,
	            "e_thaimtb_content"
	      );
	      
	      Integer value = getJdbcTemplate().queryForObject( sql, Integer.class );
	      if (value != null) {
	    	  return value + 1;
	      } else {
	    	  return 1;
	      }
    }

}
