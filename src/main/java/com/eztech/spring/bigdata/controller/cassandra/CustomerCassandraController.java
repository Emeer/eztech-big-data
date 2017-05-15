package com.eztech.spring.bigdata.controller.cassandra;

import com.eztech.spring.bigdata.persistence.domain.cassandra.Customer;
import com.eztech.spring.bigdata.service.cassandra.CustomerCassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping(path = "/cassandra", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerCassandraController {

    @Autowired
    private CustomerCassandraService customerCassandraService;


    @RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
    @Cacheable(value = "user", key = "#id")
    public Customer findById(@PathVariable UUID id) {
        return customerCassandraService.findOne(id);
    }


    @RequestMapping(path = "/customer/create", method = RequestMethod.POST)
    @CachePut(value = "user", key = "#customer.getId()")
    public Customer save(@RequestBody Customer customer) {
        return customerCassandraService.save(customer);
    }

    @RequestMapping(path = "/customer/first_name/{name}", method = RequestMethod.GET)
    @Cacheable(value = "user", key = "#name")
    public List<Customer> findByFirstName(@PathVariable String name) {
        return customerCassandraService.findByFirstName(name);
    }
}
