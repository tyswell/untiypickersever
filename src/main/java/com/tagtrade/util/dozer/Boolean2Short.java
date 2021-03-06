package com.tagtrade.util.dozer;

import org.dozer.CustomConverter;
import org.dozer.MappingException;


public class Boolean2Short implements CustomConverter {

  @SuppressWarnings("rawtypes")
  public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
    if (source == null) {
      return null;
    } else {
      if (source instanceof Boolean) {
        destination = convertBooleanToShort((Boolean) source);
        return (Short) destination;
      }
      else if (source instanceof Short) {
        destination = convertShortToBoolean((Short) source);
        return (Boolean) destination;
      }
      else {
        throw new MappingException("Converter Boolean2Short error: Arg1: "
            + destination + " and Arg2: " + source);
      }
    }
  }

  private Boolean convertShortToBoolean(Short source) {
    Boolean result = null;
    if (source.equals((short)1)) {
      result = true;
    }
    else if (source.equals((short)0)) {
      result = false;
    }
    else {
      throw new MappingException("Invalid source: should be 0 or 1 "
          + " source: " + source);
    }
    return result;
  }

  private Short convertBooleanToShort(Boolean source) {
    Short result = null;
    if (source ) {
      result = 1;
    } else {
      result = 0;
    }
    return result;
  }

}
