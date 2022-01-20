package com.nbenzekri.fastsurvey.service;

import java.util.List;

public interface IGenericService<T> {
    List<T> findAll();

    T findById(String id);

    T save(T entity);

    T update(String id, T entity);

    void deleteById(String id);
}
