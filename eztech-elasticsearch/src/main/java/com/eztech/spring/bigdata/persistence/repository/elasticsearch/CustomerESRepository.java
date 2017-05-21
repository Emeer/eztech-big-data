package com.eztech.spring.bigdata.persistence.repository.elasticsearch;


import com.eztech.spring.bigdata.persistence.domain.elasticsearch.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerESRepository extends ElasticsearchRepository<Customer, String> {

}