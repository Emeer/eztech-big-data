package com.eztech.spring.bigdata.controller.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
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

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {CassandraUnitDependencyInjectionTestExecutionListener.class})
@CassandraDataSet(value = {"simple.cql"})
@CassandraUnit
@SpringBootTest
public class CassandraControllerTest {

    private Session session;

    private MockMvc mvc;

    @InjectMocks
    private CustomerCassandraController customerCassandraController;

    @Mock
    private CustomerCassandraService customerCassandraService;

    @Before
    public void setUp() {
        Cluster cluster = Cluster.builder()
                .addContactPoints("127.0.0.1")
                .withPort(9142)
                .build();

        session = cluster.connect("cassandra_unit_keyspace");
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(customerCassandraController).build();
    }


    @Test
    public void should_have_started_and_execute_cql_script() throws Exception {
        ResultSet result = session.execute("select * from customer WHERE emails contains 'test@hotmail.com' ALLOW FILTERING");
        assertThat(result.iterator().next().getString("last_name"), is("zhou"));
    }


    @Test
    public void createCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("jia");
        customer.setLastName("zhou");
        customer.setEmails(Sets.newHashSet("test1@gmail.com", "test2@hotmail.com"));
        customer.setTopScores(Lists.newArrayList(1, 2, 3));
        customer.setTodo(Maps.newHashMap(new Date(), "today"));
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(customer);
        mvc.perform(MockMvcRequestBuilders.post("/cassandra/customer/create").content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }


    @Test
    public void findByFirstName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/cassandra/customer/first_name/eric")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.customer.last_name", is("zhou")))
    }
}
