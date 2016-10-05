package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class EContent extends DBObject {

  private Integer contentId;
  private Integer urlCode;
  private Integer facebookGropCode;
  private String title;
  private String description;
  private String contentWebId;
  private String urlContentLink;
  private Timestamp dateContentCreate;
  private Timestamp createDate;

  public Integer getContentId() {
    return contentId;
  }

  public void setContentId(Integer contentId) {
    this.contentId = contentId;
  }

  public Integer getUrlCode() {
    return urlCode;
  }

  public void setUrlCode(Integer urlCode) {
    this.urlCode = urlCode;
  }

  public Integer getFacebookGropCode() {
    return facebookGropCode;
  }

  public void setFacebookGropCode(Integer facebookGropCode) {
    this.facebookGropCode = facebookGropCode;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContentWebId() {
    return contentWebId;
  }

  public void setContentWebId(String contentWebId) {
    this.contentWebId = contentWebId;
  }

  public String getUrlContentLink() {
    return urlContentLink;
  }

  public void setUrlContentLink(String urlContentLink) {
    this.urlContentLink = urlContentLink;
  }

  public Timestamp getDateContentCreate() {
    return dateContentCreate;
  }

  public void setDateContentCreate(Timestamp dateContentCreate) {
    this.dateContentCreate = dateContentCreate;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public String toString() {
    return ("EContent{" +
        format("contentId") + ": " + format(contentId) + ", " +
        format("urlCode") + ": " + format(urlCode) + ", " +
        format("facebookGropCode") + ": " + format(facebookGropCode) + ", " +
        format("title") + ": " + format(title) + ", " +
        format("description") + ": " + format(description) + ", " +
        format("contentWebId") + ": " + format(contentWebId) + ", " +
        format("urlContentLink") + ": " + format(urlContentLink) + ", " +
        format("dateContentCreate") + ": " + format(dateContentCreate) + ", " +
        format("createDate") + ": " + format(createDate) +
      "}");
  }

}
