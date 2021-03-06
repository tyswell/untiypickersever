package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.CriteriaBuilder;
import com.tagtrade.dataacess.entity.bean.EUserDevice;

public class EUserDeviceDAO extends BaseDAO<EUserDevice> {

  private static final String FIELD_NAMES = "user_id, device_model, os_type_code, token_notification, modify_date, active";

  protected static final RowMapper<EUserDevice> ROW_MAPPER = new RowMapper<EUserDevice>() {
    public EUserDevice mapRow(ResultSet rs, int index) throws SQLException {
      EUserDevice result = new EUserDevice();

      result.setUserId( rs.getString("user_id") );
      result.setDeviceModel( rs.getString("device_model") );
      result.setOsTypeCode( (Integer) rs.getObject("os_type_code") );
      result.setTokenNotification( rs.getString("token_notification") );
      result.setModifyDate( rs.getTimestamp("modify_date") );
      result.setActive( rs.getString("active") );

      return result;
    }
  };

  public void insert(final EUserDevice eUserDevice) {
    getJdbcTemplate().update(
        "insert into e_user_device (" + FIELD_NAMES + ") values(?, ?, ?, ?, ?, ?)",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement pstmt) throws SQLException {
            pstmt.setString(1, eUserDevice.getUserId());
            pstmt.setString(2, eUserDevice.getDeviceModel());
            pstmt.setObject(3, eUserDevice.getOsTypeCode());
            pstmt.setString(4, eUserDevice.getTokenNotification());
            pstmt.setTimestamp(5, eUserDevice.getModifyDate());
            pstmt.setString(6, eUserDevice.getActive());
          }
        });
  }

  public boolean isKeyExist(final String user_id, final String device_model) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_user_device where user_id = ? and device_model = ?",
        new Object[] {
            user_id, device_model
        }, Integer.class));
  }

  public EUserDevice selectByKey(final String user_id, final String device_model) {
    List<EUserDevice> results = selectWithSuffix(
        "where user_id = ? and device_model = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, user_id);
            ps.setString(2, device_model);
          }
        });

    if (results.size() != 0) {
      return (EUserDevice) results.get(0);
    }
    else {
      return null;
    }
  }

  public List<EUserDevice> selectAll() {
    return selectWithSuffix("");
  }

  protected List<EUserDevice> selectWithSuffix(String suffixSql) {
    return (List<EUserDevice>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_device " + suffixSql,
        ROW_MAPPER);
  }

  protected List<EUserDevice> selectWithSuffix(String suffixSql, Object... args) {
    return (List<EUserDevice>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_device " + suffixSql,
        args,
        ROW_MAPPER);
  }

  protected List<EUserDevice> selectWithSuffix(String suffixSql, PreparedStatementSetter pss) {
    return (List<EUserDevice>) getJdbcTemplate().query(
        "select " + FIELD_NAMES + " from e_user_device " + suffixSql,
        pss,
        ROW_MAPPER);
  }

  public void updateByKey(final EUserDevice eUserDevice) {
    getJdbcTemplate().update(
        "update e_user_device set os_type_code = ?, token_notification = ?, modify_date = ?, active = ? where device_model = ? and user_id = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, eUserDevice.getOsTypeCode());
            ps.setString(2, eUserDevice.getTokenNotification());
            ps.setTimestamp(3, eUserDevice.getModifyDate());
            ps.setString(4, eUserDevice.getActive());
            ps.setString(5, eUserDevice.getDeviceModel());
            ps.setString(6, eUserDevice.getUserId());
          }
        });
  }

  public void deleteByKey(final String user_id, final String device_model) {
    getJdbcTemplate().update(
        "delete from e_user_device where user_id = ? and device_model = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, user_id);
            ps.setString(2, device_model);
          }
        });
  }

  /**
  *******************************************************************************
  * Code Generated on   2016-11-03,   14:40:12
  *
  * If you want to add your code, please insert it below.
  *******************************************************************************
  */
  
  public List<EUserDevice> selectAllDevice(final String user_id) {
	  CriteriaBuilder cb = new CriteriaBuilder();
	  cb.and("user_id", user_id);
	  
	  return selectWithSuffix(cb);
  }

}
