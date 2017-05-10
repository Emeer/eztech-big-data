package com.eztech.spring.bigdata.controller.elasticsearch;


import com.eztech.spring.bigdata.persistence.domain.elasticsearch.Customer;
import com.eztech.spring.bigdata.service.elasticsearch.CustomerESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Customer ES Controller
 */
@RestController
@RequestMapping(path = "/elasticsearch", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
public class CustomerESController {
    @Autowired
    private CustomerESService customerESService;


    @RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
    public Customer findById(@PathVariable String id) {
        return customerESService.findOne(id);
    }


    @RequestMapping(path = "customer/create", method = RequestMethod.POST)
    public Customer save(@RequestBody Customer customer) {
        return customerESService.save(customer);
    }

    @RequestMapping(path = "customer/first_name/{name}", method = RequestMethod.GET)
    public Page<Customer> findByFirstName(@PathVariable String name) {
        return customerESService.findByFirstName(name);
    }

}
