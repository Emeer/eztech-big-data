package com.eztech.spring.bigdata.controller.cassandra;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.UUID;

import com.eztech.spring.bigdata.persistence.domain.cassandra.Customer;
import com.eztech.spring.bigdata.service.cassandra.CustomerCassandraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestExecutionListeners(listeners = {CassandraUnitDependencyInjectionTestExecutionListener.class})
@CassandraDataSet(value = {"simple.cql"})
@CassandraUnit
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CassandraControllerTest {


    private MockMvc mvc;

    @InjectMocks
    private CustomerCassandraController customerCassandraController;

    @Mock
    private CustomerCassandraService customerCassandraService;

    private Customer customer;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(customerCassandraController).build();

        customer = new Customer();
        customer.setId(UUID.randomUUID());
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
        when(customerCassandraService.save(customer)).thenReturn(customer);
        mvc.perform(MockMvcRequestBuilders.post("/cassandra/customer/create").content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(customerCassandraService, times(1)).save(customer);
    }


    @Test
    public void findById() throws Exception {
        when(customerCassandraService.findOne(any())).thenReturn(customer);
        mvc.perform(MockMvcRequestBuilders.get("/cassandra/customer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(customerCassandraService, times(1)).findOne(any());
    }


    @Test
    public void findByFirstName() throws Exception {
        when(customerCassandraService.findByFirstName(any())).thenReturn(Lists.newArrayList(customer));
        mvc.perform(MockMvcRequestBuilders.get("/cassandra/customer/first_name/eric")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(customerCassandraService, times(1)).findByFirstName(any());
    }
}
