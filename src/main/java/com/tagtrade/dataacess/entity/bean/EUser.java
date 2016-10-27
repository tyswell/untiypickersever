package com.tagtrade.dataacess.entity.bean;

import java.sql.Date;
import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EUser extends DBObject {

  private String username;
  private String displayName;
  private String tokenUid;
  private Date tokenUidExpireDate;
  private Timestamp createDate;
  private Integer userLoginType;
  private String active;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getTokenUid() {
    return tokenUid;
  }

  public void setTokenUid(String tokenUid) {
    this.tokenUid = tokenUid;
  }

  public Date getTokenUidExpireDate() {
    return tokenUidExpireDate;
  }

  public void setTokenUidExpireDate(Date tokenUidExpireDate) {
    this.tokenUidExpireDate = tokenUidExpireDate;
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
        format("username") + ": " + format(username) + ", " +
        format("displayName") + ": " + format(displayName) + ", " +
        format("tokenUid") + ": " + format(tokenUid) + ", " +
        format("tokenUidExpireDate") + ": " + format(tokenUidExpireDate) + ", " +
        format("createDate") + ": " + format(createDate) + ", " +
        format("userLoginType") + ": " + format(userLoginType) + ", " +
        format("active") + ": " + format(active) +
      "}");
  }

}
