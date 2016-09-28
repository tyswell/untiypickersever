package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;


public class ESearching extends DBObject {

  private Integer searchingId;
  private String description;
  private Integer userId;
  private Timestamp createDate;
  private String active;

  public Integer getSearchingId() {
    return searchingId;
  }

  public void setSearchingId(Integer searchingId) {
    this.searchingId = searchingId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String toString() {
    return ("ESearching{" +
        format("searchingId") + ": " + format(searchingId) + ", " +
        format("description") + ": " + format(description) + ", " +
        format("userId") + ": " + format(userId) + ", " +
        format("createDate") + ": " + format(createDate) + ", " +
        format("active") + ": " + format(active) +
      "}");
  }

}
