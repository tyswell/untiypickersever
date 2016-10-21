package com.tagtrade.util.dozer;

import java.util.Date;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import com.tagtrade.util.DateUtil;

public class String2Date implements CustomConverter{

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

        destination = ( Date) convertStringToDate((String)source);
        return (Date) destination;
      }
      else if (source instanceof Date) {
        destination = (String) convertDateToString((Date) source);
        return destination;
      }
      else {
        throw new MappingException("Converter String2Date used incorrectly. Arguments passed in were:"
            + destination + " and " + source);
      }

    }
  }

  private static Date convertStringToDate(String str) {
    return DateUtil.parseDateThai(str);
  }

  private static String convertDateToString(Date date) {
    return DateUtil.formatDateThai(date);
  }

}
