package com.tagtrade.dataacess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CriteriaBuilder implements CriteriaBuilderFeatures {

  private static final int NO_LIMIT = -1;

  private List<String> criterias = new ArrayList<String>();
  private List<Object> parameters = null;
  private List<OrderClause> orders = new ArrayList<OrderClause>();
  private int limit = NO_LIMIT;

  public CriteriaBuilder() {
    parameters = new ArrayList<Object>();
  }

  public CriteriaBuilder(List<Object> parameters) {
    this.parameters = parameters;
  }

  //****************************************************************************

  public void and(String criteria) {
    criterias.add(criteria);
  }

  public void andClause(String criteria, Object... values) {
    criterias.add(criteria);
    for (Object value : values) {
      parameters.add(value);
    }
  }

  //----------------------------------------------------------------------------

  public void and(String name, Object value) {
    if (hasValue(value)) {
      andForce(name, value);
    }
  }

  public void andIn(String name, Integer... values) {
    if (values != null && values.length > 0) {
      StringBuilder sb = new StringBuilder();
      sb.append("(");
      for (int i = 0; i < values.length; i++) {
        if (i != 0) {
          sb.append(", ");
        }

        sb.append( values[i] );
      }
      sb.append(")");

      and(name + " in " + sb.toString());
    }
  }

  public void andIn(String name, String... values) {
    if (values != null && values.length > 0) {
      StringBuilder sb = new StringBuilder();
      sb.append("(");
      for (int i = 0; i < values.length; i++) {
        if (i != 0) {
          sb.append(", ");
        }

        sb.append( "'" + values[i] + "'" );
      }
      sb.append(")");

      and(name + " in " + sb.toString());
    }
  }

  public void andIn(String name, List<? extends Object> values) {
    if (values != null && values.size() > 0) {
      StringBuilder sb = new StringBuilder();
      sb.append("(");
      for (int i = 0; i < values.size(); i++) {
        if (i != 0) {
          sb.append(", ");
        }
        Object value = values.get(i);
        if (value instanceof String) {
          sb.append("'" + (String) value + "'");
        }
        else if (value instanceof Integer) {
          sb.append(value);
        }
      }
      sb.append(")");

      and(name + " in " + sb.toString());
    }
  }

  public void andInPreStmt(String name, List<? extends Object> values) {
    if (values != null && values.size() > 0) {

      parameters.addAll(values);

      StringBuilder sb = new StringBuilder();
      sb.append("(");
      for (int i = 0; i < values.size(); i++) {
        if (i != 0) {
          sb.append(", ");
        }
        sb.append("?");
      }
      sb.append(")");

      and(name + " in " + sb.toString());
    }
  }

  public void andInSubStmt(String name, String statement) {
    if (statement != null && !statement.equals("")) {
      StringBuilder sb = new StringBuilder();
      sb.append("(");
      sb.append(statement);
      sb.append(")");

      and(name + " in " + sb.toString());
    }
  }

  public void and(String name, Date startValue, Date endValue) {
    if (hasValue(startValue)) {
      if (hasValue(endValue)) {
        andForce(name, startValue, endValue);
      }
      else {
        and(name + " >= ?");
        parameters.add(startValue);
      }
    }
    else {
      if (hasValue(endValue)) {
        and(name + " <= ?");
        parameters.add(endValue);
      }
      else {
        // do nothing
      }
    }
  }

  public void andCustom(String name, Object value, String operator) {
    if (hasValue(value)) {
      andCustomForce(name, value, operator);
    }
  }

  public void andPartial(String name, String value) {
    if (hasValue(value)) {
      andPartialForce(name, value);
    }
  }

  public void andNull(String name) {
    and(name + " is null");
  }

  public void andNotNull(String name) {
    and(name + " is not null");
  }

  //----------------------------------------------------------------------------

  public void andForce(String name, Object value) {
    and(name + " = ?");
    parameters.add(value);
  }

  public void andForce(String name, Date startValue, Date endValue) {
    and(name + " between ? and ?");
    parameters.add(startValue);
    parameters.add(endValue);
  }

  public void andCustomForce(String name, Object value, String operator) {
    and(name + " " + operator.trim() + " ?");
    parameters.add(value);
  }

  public void andPartialForce(String name, String value) {
    if ((value != null) &&
        (value.length() >= 4) &&
        (value.indexOf('%') == -1) &&
        (value.indexOf('*') == (value.length() - 1))) {
      and(name + " like ?");
      parameters.add( value.substring(0, (value.length() - 1)) + "%" );
    }
    else {
      and(name + " = ?");
      parameters.add(value);
    }
  }

  //****************************************************************************

  public void order(String fieldName) {
    orderAsc(fieldName);
  }

  public void orderAsc(String fieldName) {
    orders.add( new OrderClause( fieldName, OrderDirection.ASC ) );
  }

  public void orderDesc(String fieldName) {
    orders.add( new OrderClause( fieldName, OrderDirection.DESC ) );
  }

  //****************************************************************************

  public void limit() {
    limit = DAOConst.SEARCH_LIMIT;
  }

  public void limit2Section() {
    limit = DAOConst.SEARCH_LIMIT_2SECTION;
  }

  public void limit(int max) {
    limit = max;
  }

  public int getLimit() {
    return limit;
  }

  //****************************************************************************

  public void clearForCount() {
    clearOrder();
    clearLimit();
  }

  public void clearOrder() {
    orders.clear();
  }

  public void clearLimit() {
    limit = NO_LIMIT;
  }

  //****************************************************************************

  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (criterias.size() != 0) {
      sb.append("where ");

      for (int i = 0; i < criterias.size(); i++) {
        String criteria = criterias.get(i);

        if (i != 0) {
          sb.append(" and ");
        }

        sb.append(criteria);
      }
    }

    if (orders.size() != 0) {
      if (criterias.size() != 0) {
        // have where
        sb.append(" ");
      }

      sb.append("order by ");

      for (int i = 0; i < orders.size(); i++) {
        OrderClause order = orders.get(i);

        if (i != 0) {
          sb.append(", ");
        }

        sb.append(order.fieldName + " ");

        if (OrderDirection.ASC == order.direction) {
          sb.append("asc");
        }
        else if (OrderDirection.DESC == order.direction) {
          sb.append("desc");
        }
      }
    }

    if (limit != NO_LIMIT) {
      if ((criterias.size() != 0) || orders.size() != 0) {
        // have where or orderby
        sb.append(" ");
      }

      sb.append("limit " + limit );// use for mysql
//      sb.append("fetch first " + limit + " rows only");// use for db2
    }

    return sb.toString();
  }

  public Object[] getParameters() {
    return parameters.toArray();
  }

  public List<Object> getParameterList() {
    return parameters;
  }
  //****************************************************************************

  private boolean hasValue(Object value) {
    if (value != null) {
      if (value instanceof String) {
        return !value.equals("");
      }
      else {
        return true;
      }
    }
    else {
      return false;
    }
  }

  //****************************************************************************
  //****************************************************************************
  //****************************************************************************

  private class OrderClause {
    String fieldName;
    OrderDirection direction;

    OrderClause(String fieldName, OrderDirection direction) {
      this.fieldName = fieldName;
      this.direction = direction;
    }
  }

  private enum OrderDirection {
    ASC,
    DESC
  }


}
