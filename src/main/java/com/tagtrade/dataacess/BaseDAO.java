package com.tagtrade.dataacess;

import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;

public abstract class BaseDAO<S extends DBObject> extends BaseDAOX {
  
  public abstract void insert(S dbObject);

  public List<S> selectAll() {
    return selectWithSuffix("");
  }

  protected List<S> selectWithSuffix(CriteriaBuilder cb) {
    return selectWithSuffix(cb.toString(), cb.getParameters());
  }

  //****************************************************************************

  protected abstract List<S> selectWithSuffix(String suffixSql);

  protected abstract List<S> selectWithSuffix(String suffixSql, Object... args);

  protected abstract List<S> selectWithSuffix(String suffixSql, PreparedStatementSetter pss);

}
