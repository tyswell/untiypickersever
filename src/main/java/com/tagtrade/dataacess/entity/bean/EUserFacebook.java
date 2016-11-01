package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EUserFacebook extends DBObject {

  private Integer userId;
  private String facebookId;
  private String tokenFacebook;
  private Timestamp modifyDate;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  public String getTokenFacebook() {
    return tokenFacebook;
  }

  public void setTokenFacebook(String tokenFacebook) {
    this.tokenFacebook = tokenFacebook;
  }

  public Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }

  public String toString() {
    return ("EUserFacebook{" +
        format("userId") + ": " + format(userId) + ", " +
        format("facebookId") + ": " + format(facebookId) + ", " +
        format("tokenFacebook") + ": " + format(tokenFacebook) + ", " +
        format("modifyDate") + ": " + format(modifyDate) +
      "}");
  }

}
