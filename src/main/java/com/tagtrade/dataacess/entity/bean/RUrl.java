package com.tagtrade.dataacess.entity.bean;

import com.tagtrade.dataacess.DBObject;

public class RUrl extends DBObject {

  private Integer code;
  private Integer webNameCode;
  private String description;
  private Integer webTypeCode;
  private String active;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Integer getWebNameCode() {
    return webNameCode;
  }

  public void setWebNameCode(Integer webNameCode) {
    this.webNameCode = webNameCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getWebTypeCode() {
    return webTypeCode;
  }

  public void setWebTypeCode(Integer webTypeCode) {
    this.webTypeCode = webTypeCode;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String toString() {
    return ("RUrl{" +
        format("code") + ": " + format(code) + ", " +
        format("webNameCode") + ": " + format(webNameCode) + ", " +
        format("description") + ": " + format(description) + ", " +
        format("webTypeCode") + ": " + format(webTypeCode) + ", " +
        format("active") + ": " + format(active) +
      "}");
  }

}
