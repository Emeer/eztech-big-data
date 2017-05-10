package com.eztech.spring.bigdata.controller.cassandra;

import com.eztech.spring.bigdata.persistence.domain.cassandra.Customer;
import com.eztech.spring.bigdata.service.cassandra.CustomerCassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping(path = "/cassandra", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
public class CustomerCassandraController {

    @Autowired
    private CustomerCassandraService customerCassandraService;


    @RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
    @Cacheable(value = "user", key = "#id")
    public Customer findById(@PathVariable UUID id) {
        return customerCassandraService.findOne(id);
    }


    @RequestMapping(path = "customer/create", method = RequestMethod.POST)
    @CachePut(value = "user", key = "#customer.id")
    public Customer save(@RequestBody Customer customer) {
        customer.setId(UUID.randomUUID());
        return customerCassandraService.save(customer);
    }

    @RequestMapping(path = "customer/first_name/{name}", method = RequestMethod.GET)
    public List<Customer> findByFirstName(@PathVariable String name) {
        return customerCassandraService.findByFirstName(name);
    }
}
