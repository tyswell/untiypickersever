package com.tagtrade.util.dozer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


public class MappingUtil {

  static {
    List<String> mappingFiles = new ArrayList<String>();
    
    List<CustomConverter> customConverters = new ArrayList<CustomConverter>();
    customConverters.add( new String2Date() );
    customConverters.add( new String2BigDecimal() );
    customConverters.add( new BooleanString2String() );
    customConverters.add( new String2Timestamp() );
    customConverters.add( new Boolean2Short() );

    DozerBeanMapper dozerMapper = new DozerBeanMapper();
    dozerMapper.setMappingFiles(mappingFiles);
    dozerMapper.setCustomConverters(customConverters);

    fMapper = dozerMapper;
  }

  private static final Mapper fMapper;

  public static void map(Object src, Object dest) {

    if (src == null) {
      throw new IllegalArgumentException("null sources");
    }


    fMapper.map(src,dest);
  }

  public static Object map(Object src, Class<?> destClass) {
    if (src == null) {
      throw new IllegalArgumentException("null sources");
    }

    return fMapper.map(src, destClass);
  }

  public static Object[] map(Object[] src, Class<?> destClass) {
    if (src == null) {
      throw new IllegalArgumentException("null sources");
    }
    if (destClass == null) {
      throw new IllegalArgumentException("null destinationClass");
    }

    Object[] result = (Object[]) Array.newInstance(destClass, src.length);

    for (int i = 0; i < src.length; i ++){
      try {
        result[i] = destClass.newInstance();
      }
      catch (Exception e) {
        throw new RuntimeException(e);
      }

      MappingUtil.map(src[i], result[i]);
    }

  return result;
  }

  public static Object[] map2Array(List<?> src, Class<?> destClass) {
    return map(src.toArray(),destClass);
  }

  @SuppressWarnings("unchecked")
  public static <T> List<T> map2List(Object[] src, Class<T> destClass) {
    return (List<T>) Arrays.asList(map(src,destClass));
  }

  @SuppressWarnings("unchecked")
  public static <T> List<T> map(List<?> src, Class<T> destClass) {
    return (List<T>) Arrays.asList(map2Array(src,destClass));
  }



}
