package com.eztech.spring.bigdata.controller.elasticsearch;


import com.eztech.spring.bigdata.persistence.domain.elasticsearch.Customer;
import com.eztech.spring.bigdata.persistence.repository.elasticsearch.CustomerESRepository;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ElasticSearchRepoTest {


    @Autowired
    private CustomerESRepository repository;


    @Test
    public void save() {

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setFirstName("eric");
        customer.setLastName("zhou");
        customer.setEmails(Sets.newHashSet("test@hotmail.com", "test@gmail.com"));
        customer.setTopScores(Lists.newArrayList(100, 200, 300));
        customer.setTodo(Maps.newHashMap(new Date(), "today"));


        Customer createdCustomer = repository.save(customer);
        assertThat(createdCustomer.getId(), is(customer.getId()));

    }


}
