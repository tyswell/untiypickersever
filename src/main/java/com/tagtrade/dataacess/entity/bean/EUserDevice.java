package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EUserDevice extends DBObject {

  private String username;
  private String deviceModel;
  private Integer osTyepCode;
  private String toeknNotification;
  private Timestamp modifyDate;
  private String active;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDeviceModel() {
    return deviceModel;
  }

  public void setDeviceModel(String deviceModel) {
    this.deviceModel = deviceModel;
  }

  public Integer getOsTyepCode() {
    return osTyepCode;
  }

  public void setOsTyepCode(Integer osTyepCode) {
    this.osTyepCode = osTyepCode;
  }

  public String getToeknNotification() {
    return toeknNotification;
  }

  public void setToeknNotification(String toeknNotification) {
    this.toeknNotification = toeknNotification;
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
        format("username") + ": " + format(username) + ", " +
        format("deviceModel") + ": " + format(deviceModel) + ", " +
        format("osTyepCode") + ": " + format(osTyepCode) + ", " +
        format("toeknNotification") + ": " + format(toeknNotification) + ", " +
        format("modifyDate") + ": " + format(modifyDate) + ", " +
        format("active") + ": " + format(active) +
      "}");
  }

}
