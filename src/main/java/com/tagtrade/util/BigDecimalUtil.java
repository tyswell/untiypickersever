package com.tagtrade.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

  public static final int SCALE = 2;
  public static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

  public static BigDecimal createBigDecimal(String val) {
    return setScale( new BigDecimal(val) );
  }

  public static BigDecimal createBigDecimal(int val) {
    return createBigDecimal(String.valueOf(val));
  }

  public static BigDecimal createBigDecimal(double val) {
    return createBigDecimal(String.valueOf(val));
  }

  public static BigDecimal setScale(BigDecimal bigDecimal) {
    if (bigDecimal != null) {
      return bigDecimal.setScale(SCALE, ROUNDING_MODE);
    }
    else {
      return null;
    }
  }

  public static double getDoubleValue(BigDecimal bigDecimal) {
    Double doubleNum = Double.valueOf(bigDecimal.toString());
    return doubleNum.doubleValue();
  }

}
