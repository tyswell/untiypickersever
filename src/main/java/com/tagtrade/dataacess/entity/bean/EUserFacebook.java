package com.tagtrade.dataacess.entity.bean;

import java.sql.Date;
import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EUserFacebook extends DBObject {

  private String facebookId;
  private Integer userId;
  private String token;
  private Date expireDate;
  private Timestamp modifyDate;

  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }

  public String toString() {
    return ("EUserFacebook{" +
        format("facebookId") + ": " + format(facebookId) + ", " +
        format("userId") + ": " + format(userId) + ", " +
        format("token") + ": " + format(token) + ", " +
        format("expireDate") + ": " + format(expireDate) + ", " +
        format("modifyDate") + ": " + format(modifyDate) +
      "}");
  }

}
