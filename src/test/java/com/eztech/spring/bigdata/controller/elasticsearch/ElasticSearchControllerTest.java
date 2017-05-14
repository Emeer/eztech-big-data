package com.eztech.spring.bigdata.controller.elasticsearch;


import com.eztech.spring.bigdata.persistence.domain.elasticsearch.Customer;
import com.eztech.spring.bigdata.service.elasticsearch.CustomerESService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ElasticSearch Controller Test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ElasticSearchControllerTest {

    private MockMvc mvc;

    private Customer customer;

    @InjectMocks
    private CustomerESController customerESController;

    @Mock
    private CustomerESService customerESService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(customerESController).build();

        customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setFirstName("jia");
        customer.setLastName("zhou");
        customer.setEmails(Sets.newHashSet("test1@gmail.com", "test2@hotmail.com"));
        customer.setTopScores(Lists.newArrayList(1, 2, 3));
        customer.setTodo(Maps.newHashMap(new Date(), "today"));
    }


    @Test
    public void createCustomer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(customer);
        when(customerESController.save(customer)).thenReturn(customer);
        mvc.perform(MockMvcRequestBuilders.post("/elasticsearch/customer/create").content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(customerESService, times(1)).save(customer);
    }


    @Test
    public void findById() throws Exception {
        when(customerESService.findOne(any())).thenReturn(customer);
        mvc.perform(MockMvcRequestBuilders.get("/elasticsearch/customer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(customerESService, times(1)).findOne(any());
    }


    @Test
    public void findByFirstName() throws Exception {
        when(customerESService.findByFirstName(any())).thenReturn(any());
        mvc.perform(MockMvcRequestBuilders.get("/elasticsearch/customer/first_name/eric")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(customerESService, times(1)).findByFirstName(any());
    }
}
