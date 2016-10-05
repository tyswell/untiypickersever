package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.SContent;

public class SContentDAO extends BaseDAO<SContent> {

  private static final String FIELD_NAMES = "id";

  protected static final RowMapper<SContent> ROW_MAPPER = new RowMapper<SContent>() {
    public SContent mapRow(ResultSet rs, int index) throws SQLException {
      SContent result = new SContent();

      result.setId( (Integer) rs.getObject("id") );

      return result;
    }
  };

  public void insert(final SContent sContent) {
    getJdbcTemplate().update(
        "insert into s_content (" + FIELD_NAMES + ") values(?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, sContent.getId());
          }
        });
  }

  public boolean isKeyExist(final Integer id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from s_content where id = ?",
        new Object[] {
            id
        }, Integer.class));
  }

  public SContent selectByKey(final Integer id) {
    List<SContent> results = selectWithSuffix(
        "where id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, id);
          }
        });

    if (results.size() != 0) {
      return (SContent) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<SContent> selectAll() {
    return selectWithSuffix("");
  }

  protected List<SContent> selectWithSuffix(String suffixSql) {
    return (List<SContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from s_content " + suffixSql,
        ROW_MAPPER);
  }

  protected List<SContent> selectWithSuffix(String suffixSql, Object... args) {
    return (List<SContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from s_content " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<SContent> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<SContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from s_content " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void deleteByKey(final Integer id) {
    getJdbcTemplate().update(
        "delete from s_content where id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-05,   11:02:00
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */
  
	public int nextSequence(){
	      String sql = strs(
	        "select",
	            "max(id)",
	        "from" ,
	            "s_content"
	      );
	      
	      Integer value = getJdbcTemplate().queryForObject( sql, Integer.class );
	      if (value != null) {
	    	  return value;
	      } else {
	    	  return 1;
	      }
	}
	
	public void deleteAll() {
	    getJdbcTemplate().update(
	        "delete from s_content",
	        new PreparedStatementSetter() {
	          public void setValues(PreparedStatement ps) throws SQLException {
	          }
	        });
	  }

}
