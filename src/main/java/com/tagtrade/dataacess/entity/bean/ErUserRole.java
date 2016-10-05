package com.tagtrade.dataacess.entity.bean;

import com.tagtrade.dataacess.DBObject;

public class ErUserRole extends DBObject {

  private Integer userId;
  private Integer roleCode;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(Integer roleCode) {
    this.roleCode = roleCode;
  }

  public String toString() {
    return ("ErUserRole{" +
        format("userId") + ": " + format(userId) + ", " +
        format("roleCode") + ": " + format(roleCode) +
      "}");
  }

}
