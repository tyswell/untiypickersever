package com.tagtrade.util.dozer;

import org.dozer.CustomConverter;
import org.dozer.MappingException;


public class BooleanString2String implements CustomConverter {

  @SuppressWarnings("rawtypes")
  public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
    if (source == null) {
      return null;
    } else {
      if (source instanceof String) {
        destination = convertStringToBoolean((String) source);
        return (Boolean) destination;
      }
      else if (source instanceof Boolean) {
        destination = convertBooleanToString((Boolean) source);
        return (String) destination;
      }
      else {
        throw new MappingException("Converter Boolean2String error: Arg1: "
            + destination + " and Arg2: " + source);
      }
    }
  }

  private Boolean convertStringToBoolean(String source) {
    Boolean result = null;
    if (source.trim().toLowerCase().equals("true") || source.trim().toLowerCase().equals("y") ) {
      result = true;
    } else if (source.trim().toLowerCase().equals("false") || source.trim().toLowerCase().equals("n") ) {
      result = false;
    }
    return result;
  }

  private String convertBooleanToString(Boolean source) {
    String result = null;
    if (source ) {
      result = "Y";
    } else {
      result = "N";
    }
    return result;
  }

}
