package com.eztech.spring.bigdata.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * ES Basic Service
 */
public interface ESBasicService<T, ID extends Serializable> extends BasicService<T, ID> {

   Iterable<T> findAll(Sort sort);

   Page<T> findAll(Pageable pageable);
}
