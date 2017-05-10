package com.eztech.spring.bigdata.controller.cassandra;

import com.eztech.spring.bigdata.persistence.domain.cassandra.Customer;
import com.eztech.spring.bigdata.service.cassandra.CustomerCassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("/cassandra")
public class CustomerCassandraController {

    @Autowired
    private CustomerCassandraService customerCassandraService;


    @RequestMapping(path = "/customer/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Customer findById(@PathVariable UUID id) {
        return customerCassandraService.findOne(id);
    }


    @RequestMapping(path = "customer/create", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public Customer save(@RequestBody Customer customer) {
        customer.setId(UUID.randomUUID());
        return customerCassandraService.save(customer);
    }

    @RequestMapping(path = "customer/first_name/{name}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Customer> findByFirstName(@PathVariable String name) {
        return customerCassandraService.findByFirstName(name);
    }
}
