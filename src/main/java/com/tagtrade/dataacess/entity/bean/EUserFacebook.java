package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EUserFacebook extends DBObject {

  private String username;
  private String tokenFacebook;
  private Timestamp modifyDate;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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
        format("username") + ": " + format(username) + ", " +
        format("tokenFacebook") + ": " + format(tokenFacebook) + ", " +
        format("modifyDate") + ": " + format(modifyDate) +
      "}");
  }

}
