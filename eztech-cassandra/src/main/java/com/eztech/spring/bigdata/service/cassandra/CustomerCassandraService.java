package com.eztech.spring.bigdata.service.cassandra;

import com.eztech.spring.bigdata.persistence.domain.cassandra.Customer;
import com.eztech.spring.bigdata.persistence.repository.cassandra.CustomerCassandraRepository;
import com.eztech.spring.bigdata.service.BasicService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * Customer Cassandra Service
 */
@Service
public class CustomerCassandraService implements BasicService<Customer, UUID> {

    @Autowired
    private CustomerCassandraRepository repository;


    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Iterable<Customer> save(Iterable<Customer> objects) {
        return repository.save(objects);
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public boolean exits(UUID id) {
        return repository.exists(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Customer findOne(UUID id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Customer> findAll(Iterable<UUID> ids) {
        return repository.findAll();
    }

    @HystrixCommand(fallbackMethod = "findByFirstName")
    public List<Customer> findByFirstName(String name) {
        return repository.findByFirstName(name);
    }

    public List<Customer> findByLastName(String name) {
        return repository.findByLastName(name);
    }


}
