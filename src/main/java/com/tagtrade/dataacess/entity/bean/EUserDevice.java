package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EUserDevice extends DBObject {

  private Integer userId;
  private String deviceModel;
  private Integer osTypeCode;
  private String tokenNotification;
  private Timestamp modifyDate;
  private String active;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getDeviceModel() {
    return deviceModel;
  }

  public void setDeviceModel(String deviceModel) {
    this.deviceModel = deviceModel;
  }

  public Integer getOsTypeCode() {
    return osTypeCode;
  }

  public void setOsTypeCode(Integer osTypeCode) {
    this.osTypeCode = osTypeCode;
  }

  public String getTokenNotification() {
    return tokenNotification;
  }

  public void setTokenNotification(String tokenNotification) {
    this.tokenNotification = tokenNotification;
  }

  public Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String toString() {
    return ("EUserDevice{" +
        format("userId") + ": " + format(userId) + ", " +
        format("deviceModel") + ": " + format(deviceModel) + ", " +
        format("osTypeCode") + ": " + format(osTypeCode) + ", " +
        format("tokenNotification") + ": " + format(tokenNotification) + ", " +
        format("modifyDate") + ": " + format(modifyDate) + ", " +
        format("active") + ": " + format(active) +
      "}");
  }

}
