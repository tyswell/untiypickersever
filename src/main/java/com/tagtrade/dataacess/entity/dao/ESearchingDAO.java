package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.CriteriaBuilder;
import com.tagtrade.dataacess.entity.bean.ESearching;

public class ESearchingDAO extends BaseDAO<ESearching> {

  private static final String FIELD_NAMES = "searching_id, description, username, search_type_code, create_date, active";

  protected static final RowMapper<ESearching> ROW_MAPPER = new RowMapper<ESearching>() {
    public ESearching mapRow(ResultSet rs, int index) throws SQLException {
      ESearching result = new ESearching();

      result.setSearchingId( (Integer) rs.getObject("searching_id") );
      result.setDescription( rs.getString("description") );
      result.setUsername( rs.getString("username") );
      result.setSearchTypeCode( (Integer) rs.getObject("search_type_code") );
      result.setCreateDate( rs.getTimestamp("create_date") );
      result.setActive( rs.getString("active") );

      return result;
    }
  };

  public void insert(final ESearching eSearching) {
    getJdbcTemplate().update(
        "insert into e_searching (" + FIELD_NAMES + ") values(?, ?, ?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setObject(1, eSearching.getSearchingId());
            pstmt.setString(2, eSearching.getDescription());
            pstmt.setString(3, eSearching.getUsername());
            pstmt.setObject(4, eSearching.getSearchTypeCode());
            pstmt.setTimestamp(5, eSearching.getCreateDate());
            pstmt.setString(6, eSearching.getActive());
          }
        });
  }

  public boolean isKeyExist(final Integer searching_id) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_searching where searching_id = ?",
        new Object[] {
            searching_id
        }, Integer.class));
  }

  public ESearching selectByKey(final Integer searching_id) {
    List<ESearching> results = selectWithSuffix(
        "where searching_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, searching_id);
          }
        });

    if (results.size() != 0) {
      return (ESearching) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<ESearching> selectAll() {
    return selectWithSuffix("");
  }

  protected List<ESearching> selectWithSuffix(String suffixSql) {
    return (List<ESearching>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_searching " + suffixSql,
        ROW_MAPPER);
  }

  protected List<ESearching> selectWithSuffix(String suffixSql, Object... args) {
    return (List<ESearching>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_searching " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<ESearching> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<ESearching>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_searching " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final ESearching eSearching) {
    getJdbcTemplate().update(
        "update e_searching set description = ?, username = ?, search_type_code = ?, create_date = ?, active = ? where searching_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, eSearching.getDescription());
            ps.setString(2, eSearching.getUsername());
            ps.setObject(3, eSearching.getSearchTypeCode());
            ps.setTimestamp(4, eSearching.getCreateDate());
            ps.setString(5, eSearching.getActive());
            ps.setObject(6, eSearching.getSearchingId());
          }
        });
  }

  public void deleteByKey(final Integer searching_id) {
    getJdbcTemplate().update(
        "delete from e_searching where searching_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, searching_id);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-10-12,   13:33:32
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */
  
  public List<ESearching> getSearch(String active, List<Integer> searchTypeCode) {
	  CriteriaBuilder cb = new CriteriaBuilder();
	  cb.andIn("search_type_code", searchTypeCode);
	  cb.and("active", active);
	  return selectWithSuffix(cb);
  }
  
  public int nextSequence(){
      String sql = strs(
        "select",
            "max(searching_id)",
        "from" ,
            "e_searching"
      );
      return getJdbcTemplate().queryForObject( sql, Integer.class ) + 1;
  }

}
