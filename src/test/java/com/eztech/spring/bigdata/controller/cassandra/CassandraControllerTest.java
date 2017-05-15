package com.eztech.spring.bigdata.controller.cassandra;

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
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestExecutionListeners(listeners = {CassandraUnitDependencyInjectionTestExecutionListener.class})
@CassandraDataSet(value = {"simple.cql"})
@CassandraUnit
@RunWith(SpringRunner.class)
@SpringBootTest
public class CassandraControllerTest {


    private MockMvc mvc;

    @InjectMocks
    private CustomerCassandraController customerCassandraController;

    @Mock
    private CustomerCassandraService customerCassandraService;

    private Customer customer;

    @Mock
    private RedisConnection redisConnectionMock;

    @Mock
    private RedisConnectionFactory redisConnectionFactoryMock;

    private RedisTemplate redisTemplate;

    private RedisCacheManager cacheManager;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(customerCassandraController).build();

        when(redisConnectionFactoryMock.getConnection()).thenReturn(redisConnectionMock);

        redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactoryMock);
        redisTemplate.afterPropertiesSet();

        cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.afterPropertiesSet();

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
        Cache.ValueWrapper cache = cacheManager.getCache("user").get(customer.getId());
        assertThat(cache.get(), is(customer));
    }


    @Test
    public void findById() throws Exception {
        when(customerCassandraService.findOne(any())).thenReturn(customer);
        String id = UUID.randomUUID().toString();
        mvc.perform(MockMvcRequestBuilders.get("/cassandra/customer/" + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(customerCassandraService, times(1)).findOne(any());
        Cache.ValueWrapper cache = cacheManager.getCache("user").get(customer);
        assertThat(cache.get(), is(customer));
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
