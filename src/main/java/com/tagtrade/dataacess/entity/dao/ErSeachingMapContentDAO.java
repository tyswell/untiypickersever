package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.ErSeachingMapContent;

public class ErSeachingMapContentDAO extends BaseDAO<ErSeachingMapContent> {

  private static final String FIELD_NAMES = "searching_id, content_id, create_date, score_hit";

  protected static final RowMapper<ErSeachingMapContent> ROW_MAPPER = new RowMapper<ErSeachingMapContent>() {
    public ErSeachingMapContent mapRow(ResultSet rs, int index) throws SQLException {
      ErSeachingMapContent result = new ErSeachingMapContent();

      result.setSearchingId( (Integer) rs.getObject("searching_id") );
      result.setContentId( (Integer) rs.getObject("content_id") );
      result.setCreateDate( rs.getTimestamp("create_date") );
      result.setScoreHit( rs.getFloat("score_hit") );

      return result;
    }
  };

  public void insert(final ErSeachingMapContent erSeachingMapContent) {
    getJdbcTemplate().update(
        "insert into er_seaching_map_content (" + FIELD_NAMES + ") values(?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, erSeachingMapContent.getSearchingId());
            pstmt.setObject(2, erSeachingMapContent.getContentId());
            pstmt.setTimestamp(3, erSeachingMapContent.getCreateDate());
            pstmt.setFloat(4, erSeachingMapContent.getScoreHit());
          }
        });
  }

  public boolean isKeyExist(final Integer searching_id, final Integer content_id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from er_seaching_map_content where searching_id = ? and content_id = ?",
        new Object[] {
            searching_id, content_id
        }, Integer.class));
  }

  public ErSeachingMapContent selectByKey(final Integer searching_id, final Integer content_id) {
    List<ErSeachingMapContent> results = selectWithSuffix(
        "where searching_id = ? and content_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, searching_id);
            ps.setObject(2, content_id);
          }
        });

    if (results.size() != 0) {
      return (ErSeachingMapContent) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<ErSeachingMapContent> selectAll() {
    return selectWithSuffix("");
  }

  protected List<ErSeachingMapContent> selectWithSuffix(String suffixSql) {
    return (List<ErSeachingMapContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_seaching_map_content " + suffixSql,
        ROW_MAPPER);
  }

  protected List<ErSeachingMapContent> selectWithSuffix(String suffixSql, Object... args) {
    return (List<ErSeachingMapContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_seaching_map_content " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<ErSeachingMapContent> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<ErSeachingMapContent>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from er_seaching_map_content " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final ErSeachingMapContent erSeachingMapContent) {
    getJdbcTemplate().update(
        "update er_seaching_map_content set create_date = ?, score_hit = ? where content_id = ? and searching_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setTimestamp(1, erSeachingMapContent.getCreateDate());
            ps.setFloat(2, erSeachingMapContent.getScoreHit());
            ps.setObject(3, erSeachingMapContent.getSearchingId());
            ps.setObject(4, erSeachingMapContent.getContentId());
          }
        });
  }

  public void deleteByKey(final Integer searching_id, final Integer content_id) {
    getJdbcTemplate().update(
        "delete from er_seaching_map_content where searching_id = ? and content_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, searching_id);
            ps.setObject(2, content_id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-03,   16:11:19
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */
  
  public void deleteOldContent(int oldDate) {
	  String sql = "DELETE FROM er_seaching_map_content WHERE CONTENT_ID in (select CONTENT_ID from e_content WHERE create_date < DATE_SUB(NOW(), INTERVAL "+oldDate+" DAY))";
	  getJdbcTemplate().update(sql);
  }

}
