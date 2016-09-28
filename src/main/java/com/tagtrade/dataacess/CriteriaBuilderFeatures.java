package com.tagtrade.dataacess;

import java.util.Date;
import java.util.List;

public interface CriteriaBuilderFeatures {

  public void and(String criteria);

  public void andClause(String criteria, Object... values);

  //----------------------------------------------------------------------------

  public void and(String name, Object value);

  public void andIn(String name, Integer... values);

  public void andIn(String name, String... values);

  public void andIn(String name, List<? extends Object> values);

  public void andInSubStmt(String name, String statement);

  public void and(String name, Date startValue, Date endValue);

  public void andCustom(String name, Object value, String operator);

  public void andPartial(String name, String value);

  public void andNull(String name);

  public void andNotNull(String name);

  //----------------------------------------------------------------------------

  public void andForce(String name, Object value);

  public void andForce(String name, Date startValue, Date endValue);

  public void andCustomForce(String name, Object value, String operator);

  public void andPartialForce(String name, String value);

  //****************************************************************************

  public void order(String fieldName);

  public void orderAsc(String fieldName);

  public void orderDesc(String fieldName);

  //****************************************************************************

  public void limit();

  public void limit2Section();

  public void limit(int max);

  public int getLimit();

  //****************************************************************************

  public void clearForCount();

  public void clearOrder();

  public void clearLimit();

  //****************************************************************************

  public String toString();

  public Object[] getParameters();

}