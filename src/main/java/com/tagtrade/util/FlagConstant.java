package com.tagtrade.util;

public class FlagConstant {

  /**
   * active
   */
  public static String ACTIVE = "Y";

  /**
   * inactive
   */
  public static String INACTIVE = "N";

  public static Boolean convertToBoolean(String booleanString) {
    if(booleanString==null) {
      return null;
    }

    if(booleanString.equals(ACTIVE)) {
      return Boolean.TRUE;
    }
    else if(booleanString.equals(INACTIVE)) {
      return Boolean.FALSE;
    }

    throw new IllegalArgumentException("Invalid input: "+booleanString);
  }

  public static String convertToBooleanString(Boolean booleanVal) {
    if(booleanVal==null) {
      return null;
    }
    if(booleanVal.booleanValue()==true) {
      return ACTIVE;
    }
    else {
      return INACTIVE;
    }
  }

}
