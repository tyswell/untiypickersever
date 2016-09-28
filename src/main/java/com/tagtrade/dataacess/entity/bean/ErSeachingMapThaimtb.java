package com.tagtrade.dataacess.entity.bean;

import java.sql.Timestamp;

import com.tagtrade.dataacess.DBObject;


public class ErSeachingMapThaimtb extends DBObject {

  private Integer searchingId;
  private Integer thaimtbId;
  private Timestamp createDate;
  private Float scoreHit;

  public Integer getSearchingId() {
    return searchingId;
  }

  public void setSearchingId(Integer searchingId) {
    this.searchingId = searchingId;
  }

  public Integer getThaimtbId() {
    return thaimtbId;
  }

  public void setThaimtbId(Integer thaimtbId) {
    this.thaimtbId = thaimtbId;
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
    return ("ErSeachingMapThaimtb{" +
        format("searchingId") + ": " + format(searchingId) + ", " +
        format("thaimtbId") + ": " + format(thaimtbId) + ", " +
        format("createDate") + ": " + format(createDate) + ", " +
        format("scoreHit") + ": " + format(scoreHit) +
      "}");
  }

}
