package com.eztech.spring.bigdata.persistence.repository.elasticsearch;


import com.eztech.spring.bigdata.persistence.domain.elasticsearch.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerESRepository extends ElasticsearchRepository<Customer, String> {

    public Customer findByFirstName(String firstName);

    public List<Customer> findByLastName(String lastName);

}