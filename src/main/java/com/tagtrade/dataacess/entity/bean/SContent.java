package com.tagtrade.dataacess.entity.bean;

import com.tagtrade.dataacess.DBObject;

public class SContent extends DBObject {

  private Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String toString() {
    return ("SContent{" +
        format("id") + ": " + format(id) +
      "}");
  }

}
