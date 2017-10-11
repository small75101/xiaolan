package com.xiaolan.common.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T, ID extends Serializable> {
    T save(T t);

    void delete(ID id);

    T update(T t);

    T findOne(ID id);

    <S extends T> List<S> findAll(T t);
}
