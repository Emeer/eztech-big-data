package com.eztech.spring.bigdata.service.elasticsearch;

import com.eztech.spring.bigdata.persistence.domain.elasticsearch.Customer;
import com.eztech.spring.bigdata.persistence.repository.elasticsearch.CustomerESRepository;
import com.eztech.spring.bigdata.service.ESBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Customer ES Service
 */
@Service
public class CustomerESService implements ESBasicService<Customer, String> {

    @Autowired
    private CustomerESRepository repository;

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Iterable<Customer> save(Iterable<Customer> customers) {
        return repository.save(customers);
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public boolean exits(String id) {
        return repository.exists(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Customer findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Customer> findAll(Iterable<String> ids) {
        return repository.findAll(ids);
    }

    @Override
    public Iterable<Customer> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Customer> findByFirstName(String firstName) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).withFilter(boolQuery().must(termQuery("emails", firstName))).build();
        return repository.search(searchQuery);
    }
}
