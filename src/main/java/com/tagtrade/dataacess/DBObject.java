package com.tagtrade.dataacess;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Type of DBObject field
 *   - BigDecimal
 *   - Date
 *   - Integer
 *   - String
 *   - Timestamp
 */
public abstract class DBObject {

  private static final BigDecimal ZERO = new BigDecimal("0.00");

  protected String format(BigDecimal decimal) {
    if (decimal != null) {
      return ZERO.add(decimal).setScale(2, RoundingMode.HALF_UP).toString();
    }
    else {
      return "null";
    }
  }

  protected String format(Date date) {
    if (date != null) {
      return "'" + new SimpleDateFormat("dd/MM/yyyy").format(date) + "'";
    }
    else {
      return "null";
    }
  }

  protected String format(Number n) {
    if (n != null) {
      return n.toString();
    }
    else {
      return "null";
    }
  }

  protected String format(String str) {
    if (str != null) {
      return "\"" + str + "\"";
    }
    else {
      return "null";
    }
  }

  protected String format(Timestamp timestamp) {
    if (timestamp != null) {
      return "'" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(timestamp) + "'";
    }
    else {
      return "null";
    }
  }

}
