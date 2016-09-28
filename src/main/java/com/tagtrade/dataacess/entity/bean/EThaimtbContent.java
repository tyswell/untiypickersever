package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;


public class EThaimtbContent extends DBObject {

  private Integer thaimtbId;
  private Integer urlCode;
  private String description;
  private String urlContentLink;
  private String dateContentCreate;
  private Timestamp createDate;

  public Integer getThaimtbId() {
    return thaimtbId;
  }

  public void setThaimtbId(Integer thaimtbId) {
    this.thaimtbId = thaimtbId;
  }

  public Integer getUrlCode() {
    return urlCode;
  }

  public void setUrlCode(Integer urlCode) {
    this.urlCode = urlCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrlContentLink() {
    return urlContentLink;
  }

  public void setUrlContentLink(String urlContentLink) {
    this.urlContentLink = urlContentLink;
  }

  public String getDateContentCreate() {
    return dateContentCreate;
  }

  public void setDateContentCreate(String dateContentCreate) {
    this.dateContentCreate = dateContentCreate;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public String toString() {
    return ("EThaimtbContent{" +
        format("thaimtbId") + ": " + format(thaimtbId) + ", " +
        format("urlCode") + ": " + format(urlCode) + ", " +
        format("description") + ": " + format(description) + ", " +
        format("urlContentLink") + ": " + format(urlContentLink) + ", " +
        format("dateContentCreate") + ": " + format(dateContentCreate) + ", " +
        format("createDate") + ": " + format(createDate) +
      "}");
  }

}
