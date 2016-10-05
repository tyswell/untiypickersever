package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.EContent;

public class EContentDAO extends BaseDAO<EContent> {

  private static final String FIELD_NAMES = "content_id, url_code, facebook_grop_code, title, description, content_web_id, url_content_link, date_content_create, create_date";

  protected static final RowMapper<EContent> ROW_MAPPER = new RowMapper<EContent>() {
    public EContent mapRow(ResultSet rs, int index) throws SQLException {
      EContent result = new EContent();

      result.setContentId( (Integer) rs.getObject("content_id") );
      result.setUrlCode( (Integer) rs.getObject("url_code") );
      result.setFacebookGropCode( (Integer) rs.getObject("facebook_grop_code") );
      result.setTitle( rs.getString("title") );
      result.setDescription( rs.getString("description") );
      result.setContentWebId( rs.getString("content_web_id") );
      result.setUrlContentLink( rs.getString("url_content_link") );
      result.setDateContentCreate( rs.getTimestamp("date_content_create") );
      result.setCreateDate( rs.getTimestamp("create_date") );

      return result;
    }
  };

  public void insert(final EContent eContent) {
    getJdbcTemplate().update(
        "insert into e_content (" + FIELD_NAMES + ") values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, eContent.getContentId());
            pstmt.setObject(2, eContent.getUrlCode());
            pstmt.setObject(3, eContent.getFacebookGropCode());
            pstmt.setString(4, eContent.getTitle());
            pstmt.setString(5, eContent.getDescription());
            pstmt.setString(6, eContent.getContentWebId());
            pstmt.setString(7, eContent.getUrlContentLink());
            pstmt.setTimestamp(8, eContent.getDateContentCreate());
            pstmt.setTimestamp(9, eContent.getCreateDate());
          }
        });
  }

  public boolean isKeyExist(final Integer content_id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_content where content_id = ?",
        new Object[] {
            content_id
        }, Integer.class));
  }

  public EContent selectByKey(final Integer content_id) {
    List<EContent> results = selectWithSuffix(
        "where content_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, content_id);
          }
        });

    if (results.size() != 0) {
      return (EContent) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<EContent> selectAll() {
    return selectWithSuffix("");
  }

  protected List<EContent> selectWithSuffix(String suffixSql) {
    return (List<EContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_content " + suffixSql,
        ROW_MAPPER);
  }

  protected List<EContent> selectWithSuffix(String suffixSql, Object... args) {
    return (List<EContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_content " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<EContent> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<EContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_content " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final EContent eContent) {
    getJdbcTemplate().update(
        "update e_content set url_code = ?, facebook_grop_code = ?, title = ?, description = ?, content_web_id = ?, url_content_link = ?, date_content_create = ?, create_date = ? where content_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, eContent.getUrlCode());
            ps.setObject(2, eContent.getFacebookGropCode());
            ps.setString(3, eContent.getTitle());
            ps.setString(4, eContent.getDescription());
            ps.setString(5, eContent.getContentWebId());
            ps.setString(6, eContent.getUrlContentLink());
            ps.setTimestamp(7, eContent.getDateContentCreate());
            ps.setTimestamp(8, eContent.getCreateDate());
            ps.setObject(9, eContent.getContentId());
          }
        });
  }

  public void deleteByKey(final Integer content_id) {
    getJdbcTemplate().update(
        "delete from e_content where content_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, content_id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-04,   15:39:24
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */
  
	public EContent selectLastestId(int urlCode) {
		String sql = strs(
				"where content_id =",
					"(",
						"select max(content_id)",
							"from e_content",
							"where url_code =" + urlCode,
					")"
		);
		List<EContent> results = selectWithSuffix(
				sql, new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
					}
				});

		if (results.size() != 0) {
			return (EContent) results.get(0);
		} else {
			return null;
		}
	}
	
	public int nextSequence(){
	      String sql = strs(
	        "select",
	            "max(content_id)",
	        "from" ,
	            "e_content"
	      );
	      
	      Integer value = getJdbcTemplate().queryForObject( sql, Integer.class );
	      if (value != null) {
	    	  return value + 1;
	      } else {
	    	  return 1;
	      }
	}
	
	public EContent selectLastestFacebookCode(int facebookGroupCode) {
		String sql = strs(
				"where content_id =",
					"(",
						"select max(content_id)",
							"from e_content",
							"where facebook_grop_code =" + facebookGroupCode,
					")"
		);
		List<EContent> results = selectWithSuffix(
				sql, new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
					}
				});

		if (results.size() != 0) {
			return (EContent) results.get(0);
		} else {
			return null;
		}
	}

}
