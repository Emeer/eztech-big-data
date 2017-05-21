package com.eztech.spring.bigdata.service;

import java.io.Serializable;

/**
 * Basic Service
 */
public interface BasicService<T, ID extends Serializable> {

    /**
     * @param object
     * @return
     */
    T save(T object);

    /**
     * @param objects
     * @return
     */
    Iterable<T> save(Iterable<T> objects);


    /**
     * @param object
     */
    void delete(T object);

    /**
     * @param id
     */
    void delete(ID id);

    /**
     *
     */
    void deleteAll();

    /**
     * @param id
     * @return
     */
    boolean exits(ID id);

    /**
     * @return
     */
    long count();

    /**
     * @param id
     * @return
     */
    T findOne(ID id);

    /**
     * @return
     */
    Iterable<T> findAll();


    /**
     * @param ids
     * @return
     */
    Iterable<T> findAll(Iterable<ID> ids);


}
