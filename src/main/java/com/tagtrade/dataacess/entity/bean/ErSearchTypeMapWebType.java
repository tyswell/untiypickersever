package com.tagtrade.dataacess.entity.bean;

import com.tagtrade.dataacess.DBObject;

public class ErSearchTypeMapWebType extends DBObject {

  private Integer searchTypeCode;
  private Integer webTypeCode;

  public Integer getSearchTypeCode() {
    return searchTypeCode;
  }

  public void setSearchTypeCode(Integer searchTypeCode) {
    this.searchTypeCode = searchTypeCode;
  }

  public Integer getWebTypeCode() {
    return webTypeCode;
  }

  public void setWebTypeCode(Integer webTypeCode) {
    this.webTypeCode = webTypeCode;
  }

  public String toString() {
    return ("ErSearchTypeMapWebType{" +
        format("searchTypeCode") + ": " + format(searchTypeCode) + ", " +
        format("webTypeCode") + ": " + format(webTypeCode) +
      "}");
  }

}
