package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;


public class EUser extends DBObject {

  private Integer userId;
  private Timestamp createDate;
  private Integer userLoginType;
  private String active;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public Integer getUserLoginType() {
    return userLoginType;
  }

  public void setUserLoginType(Integer userLoginType) {
    this.userLoginType = userLoginType;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String toString() {
    return ("EUser{" +
        format("userId") + ": " + format(userId) + ", " +
        format("createDate") + ": " + format(createDate) + ", " +
        format("userLoginType") + ": " + format(userLoginType) + ", " +
        format("active") + ": " + format(active) +
      "}");
  }

}
