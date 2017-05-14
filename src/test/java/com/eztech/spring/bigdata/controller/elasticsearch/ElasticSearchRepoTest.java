package com.eztech.spring.bigdata.controller.elasticsearch;


import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.eztech.spring.bigdata.persistence.domain.elasticsearch.Customer;
import com.eztech.spring.bigdata.persistence.repository.elasticsearch.CustomerESRepository;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

import static org.elasticsearch.test.ESIntegTestCase.Scope.TEST;
import static org.hamcrest.core.Is.is;


@RunWith(RandomizedRunner.class)
@ESIntegTestCase.ClusterScope(scope = TEST, numDataNodes = 1)
public class ElasticSearchRepoTest extends ESIntegTestCase {


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
