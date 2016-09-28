package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.ErSeachingMapThaimtb;

public class ErSeachingMapThaimtbDAO extends BaseDAO<ErSeachingMapThaimtb> {

  private static final String FIELD_NAMES = "searching_id, thaimtb_id, create_date, score_hit";

  protected static final RowMapper<ErSeachingMapThaimtb> ROW_MAPPER = new RowMapper<ErSeachingMapThaimtb>() {
    public ErSeachingMapThaimtb mapRow(ResultSet rs, int index) throws SQLException {
      ErSeachingMapThaimtb result = new ErSeachingMapThaimtb();

      result.setSearchingId( (Integer) rs.getObject("searching_id") );
      result.setThaimtbId( (Integer) rs.getObject("thaimtb_id") );
      result.setCreateDate( rs.getTimestamp("create_date") );
      result.setScoreHit( rs.getFloat("score_hit") );

      return result;
    }
  };

  public void insert(final ErSeachingMapThaimtb erSeachingMapThaimtb) {
    getJdbcTemplate().update(
        "insert into " + "er_seaching_map_thaimtb (" + FIELD_NAMES + ") values(?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, erSeachingMapThaimtb.getSearchingId());
            pstmt.setObject(2, erSeachingMapThaimtb.getThaimtbId());
            pstmt.setTimestamp(3, erSeachingMapThaimtb.getCreateDate());
            pstmt.setFloat(4, erSeachingMapThaimtb.getScoreHit());
          }
        });
  }

  public boolean isKeyExist(final Integer searching_id, final Integer thaimtb_id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from " + "er_seaching_map_thaimtb where searching_id = ? and thaimtb_id = ?",
        new Object[] {
            searching_id, thaimtb_id
        }, Integer.class));
  }

  public ErSeachingMapThaimtb selectByKey(final Integer searching_id, final Integer thaimtb_id) {
    List<ErSeachingMapThaimtb> results = selectWithSuffix(
        "where searching_id = ? and thaimtb_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, searching_id);
            ps.setObject(2, thaimtb_id);
          }
        });

    if (results.size() != 0) {
      return (ErSeachingMapThaimtb) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<ErSeachingMapThaimtb> selectAll() {
    return selectWithSuffix("");
  }

  protected List<ErSeachingMapThaimtb> selectWithSuffix(String suffixSql) {
    return (List<ErSeachingMapThaimtb>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from " + "er_seaching_map_thaimtb " + suffixSql,
        ROW_MAPPER);
  }

  protected List<ErSeachingMapThaimtb> selectWithSuffix(String suffixSql, Object... args) {
    return (List<ErSeachingMapThaimtb>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from " + "er_seaching_map_thaimtb " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<ErSeachingMapThaimtb> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<ErSeachingMapThaimtb>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from " + "er_seaching_map_thaimtb " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final ErSeachingMapThaimtb erSeachingMapThaimtb) {
    getJdbcTemplate().update(
        "update " + "er_seaching_map_thaimtb set create_date = ?, score_hit = ? where searching_id = ? and thaimtb_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setTimestamp(1, erSeachingMapThaimtb.getCreateDate());
            ps.setFloat(2, erSeachingMapThaimtb.getScoreHit());
            ps.setObject(3, erSeachingMapThaimtb.getSearchingId());
            ps.setObject(4, erSeachingMapThaimtb.getThaimtbId());
          }
        });
  }

  public void deleteByKey(final Integer searching_id, final Integer thaimtb_id) {
    getJdbcTemplate().update(
        "delete from " + "er_seaching_map_thaimtb where searching_id = ? and thaimtb_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, searching_id);
            ps.setObject(2, thaimtb_id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-07-13,   13:19:28
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */

}
