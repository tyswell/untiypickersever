package com.tagtrade.util.dozer;

import java.sql.Timestamp;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import com.tagtrade.util.TimestampUtil;


public class String2Timestamp implements CustomConverter {

  @SuppressWarnings("rawtypes")
  public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
    if (source == null) {
      return null;
    }
    else {
      if (source instanceof String) {
        if (source.toString().trim().equals("")) {
          return null;
        }

        destination = ( Timestamp) convertStringToTimestamp((String)source);
        return (Timestamp) destination;
      }
      else if (source instanceof Timestamp) {

        destination = (String) convertTimestampToString((Timestamp) source);
        return destination;
      }
      else {
        throw new MappingException("Converter String2Timestamp used incorrectly. Arguments passed in were:"
            + destination + " and " + source);
      }

    }
  }

  private static Timestamp convertStringToTimestamp(String str) {
    return TimestampUtil.stringToTimestampThaiFormat(str);
  }

  private static String convertTimestampToString(Timestamp timestamp) {
    return TimestampUtil.timestampToStringThaiFormat(timestamp);
  }

}
