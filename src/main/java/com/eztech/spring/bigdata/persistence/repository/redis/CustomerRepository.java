package com.eztech.spring.bigdata.persistence.repository.redis;

import com.eztech.spring.bigdata.persistence.domain.redis.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, String> {
}
