package com.tagtrade.dataacess.entity.bean;

import com.tagtrade.dataacess.DBObject;

public class RFacebookGroup extends DBObject {

  private Integer code;
  private String groupname;
  private String groupid;
  private Integer webTypeCode;
  private String active;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getGroupname() {
    return groupname;
  }

  public void setGroupname(String groupname) {
    this.groupname = groupname;
  }

  public String getGroupid() {
    return groupid;
  }

  public void setGroupid(String groupid) {
    this.groupid = groupid;
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
    return ("RFacebookGroup{" +
        format("code") + ": " + format(code) + ", " +
        format("groupname") + ": " + format(groupname) + ", " +
        format("groupid") + ": " + format(groupid) + ", " +
        format("webTypeCode") + ": " + format(webTypeCode) + ", " +
        format("active") + ": " + format(active) +
      "}");
  }

}
