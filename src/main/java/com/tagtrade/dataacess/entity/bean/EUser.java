package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EUser extends DBObject {

  private String userId;
  private String email;
  private String displayName;
  private Timestamp createDate;
  private Integer userLoginType;
  private String active;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
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
        format("email") + ": " + format(email) + ", " +
        format("displayName") + ": " + format(displayName) + ", " +
        format("createDate") + ": " + format(createDate) + ", " +
        format("userLoginType") + ": " + format(userLoginType) + ", " +
        format("active") + ": " + format(active) +
      "}");
  }

}
