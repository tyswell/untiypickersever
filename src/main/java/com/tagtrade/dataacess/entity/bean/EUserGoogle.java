package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EUserGoogle extends DBObject {

  private String userId;
  private String googleId;
  private String tokenGoogle;
  private Timestamp modifyDate;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }

  public String getTokenGoogle() {
    return tokenGoogle;
  }

  public void setTokenGoogle(String tokenGoogle) {
    this.tokenGoogle = tokenGoogle;
  }

  public Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }

  public String toString() {
    return ("EUserGoogle{" +
        format("userId") + ": " + format(userId) + ", " +
        format("googleId") + ": " + format(googleId) + ", " +
        format("tokenGoogle") + ": " + format(tokenGoogle) + ", " +
        format("modifyDate") + ": " + format(modifyDate) +
      "}");
  }

}
