package com.tagtrade.dataacess.entity.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.tagtrade.dataacess.BaseDAO;
import com.tagtrade.dataacess.entity.bean.EUserDevice;

public class EUserDeviceDAO extends BaseDAO<EUserDevice> {

  private static final String FIELD_NAMES = "username, device_model, os_tyep_code, toekn_notification, modify_date, active";

  protected static final RowMapper<EUserDevice> ROW_MAPPER = new RowMapper<EUserDevice>() {
    public EUserDevice mapRow(ResultSet rs, int index) throws SQLException {
      EUserDevice result = new EUserDevice();

      result.setUsername( rs.getString("username") );
      result.setDeviceModel( rs.getString("device_model") );
      result.setOsTyepCode( (Integer) rs.getObject("os_tyep_code") );
      result.setToeknNotification( rs.getString("toekn_notification") );
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
            pstmt.setString(1, eUserDevice.getUsername());
            pstmt.setString(2, eUserDevice.getDeviceModel());
            pstmt.setObject(3, eUserDevice.getOsTyepCode());
            pstmt.setString(4, eUserDevice.getToeknNotification());
            pstmt.setTimestamp(5, eUserDevice.getModifyDate());
            pstmt.setString(6, eUserDevice.getActive());
          }
        });
  }

  public boolean isKeyExist(final String username, final String device_model) {
    return (0 != getJdbcTemplate().queryForObject(
        "select count(*) from e_user_device where username = ? and device_model = ?",
        new Object[] {
            username, device_model
        }, Integer.class));
  }

  public EUserDevice selectByKey(final String username, final String device_model) {
    List<EUserDevice> results = selectWithSuffix(
        "where username = ? and device_model = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, username);
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
        "update e_user_device set os_tyep_code = ?, toekn_notification = ?, modify_date = ?, active = ? where device_model = ? and username = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, eUserDevice.getOsTyepCode());
            ps.setString(2, eUserDevice.getToeknNotification());
            ps.setTimestamp(3, eUserDevice.getModifyDate());
            ps.setString(4, eUserDevice.getActive());
            ps.setString(5, eUserDevice.getUsername());
            ps.setString(6, eUserDevice.getDeviceModel());
          }
        });
  }

  public void deleteByKey(final String username, final String device_model) {
    getJdbcTemplate().update(
        "delete from e_user_device where username = ? and device_model = ?",
        new PreparedStatementSetter() {
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, username);
            ps.setString(2, device_model);
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

}
