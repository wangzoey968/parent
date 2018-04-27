package com.it.dao.base;

import java.util.List;

public interface BaseDao<T> {

    public T insert(T var);

    public T delete(T var);

    public T update(T var);

    //public List<T> select(T var);

}
