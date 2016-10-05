package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;

public class ErSeachingMapContent extends DBObject {

  private Integer searchingId;
  private Integer contentId;
  private Timestamp createDate;
  private Float scoreHit;

  public Integer getSearchingId() {
    return searchingId;
  }

  public void setSearchingId(Integer searchingId) {
    this.searchingId = searchingId;
  }

  public Integer getContentId() {
    return contentId;
  }

  public void setContentId(Integer contentId) {
    this.contentId = contentId;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public Float getScoreHit() {
    return scoreHit;
  }

  public void setScoreHit(Float scoreHit) {
    this.scoreHit = scoreHit;
  }

  public String toString() {
    return ("ErSeachingMapContent{" +
        format("searchingId") + ": " + format(searchingId) + ", " +
        format("contentId") + ": " + format(contentId) + ", " +
        format("createDate") + ": " + format(createDate) + ", " +
        format("scoreHit") + ": " + format(scoreHit) +
      "}");
  }

}
