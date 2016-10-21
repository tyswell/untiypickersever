package com.tagtrade.util.dozer;

import java.math.BigDecimal;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import com.tagtrade.util.NumberUtil;

public class String2BigDecimal implements CustomConverter{

  @SuppressWarnings("rawtypes")
  public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
    if (source == null) {
      return null;
    }

    if (source instanceof String) {
      if (source.toString().trim().equals("")) {
        return null;
      } else {
        destination = convertStringToBigDecimal((String)source);
        return (BigDecimal) destination;
      }
    }
    else if (source instanceof BigDecimal) {
      destination = convertBigDecimalToString((BigDecimal) source);
      return destination;
    }

    else {
      throw new MappingException("Converter String2BigDecimal used incorrectly. Arguments passed in were:"
          + destination + " and " + source);
    }


  }

  private BigDecimal convertStringToBigDecimal(String str) {
    return new BigDecimal(str.replaceAll(",", ""));
  }

  private String convertBigDecimalToString(BigDecimal bigDecimal) {
    return NumberUtil.insertCommaMoney(bigDecimal);
  }

}