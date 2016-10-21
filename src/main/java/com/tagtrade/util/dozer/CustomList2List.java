package com.tagtrade.util.dozer;

import java.util.List;

import org.dozer.CustomConverter;


public class CustomList2List implements CustomConverter{

  @SuppressWarnings("rawtypes")
  public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
    if (source == null) {
      return null;
    }
    else {
      return (List) source;

//      if ( true) {
//        return (List)source;
//      }
//      else {
//        throw new MappingException("Converter CustomList2List used incorrectly. Arguments passed in were:"
//            + destination + " and " + source);
//      }
    }
  }

}
