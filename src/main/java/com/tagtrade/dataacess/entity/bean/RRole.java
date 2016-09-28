package com.tagtrade.dataacess.entity.bean;

import com.tagtrade.dataacess.DBObject;


public class RRole extends DBObject {

  private Integer code;
  private String description;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String toString() {
    return ("RRole{" +
        format("code") + ": " + format(code) + ", " +
        format("description") + ": " + format(description) +
      "}");
  }

}
