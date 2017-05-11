package com.eztech.spring.bigdata.controller.cassandra;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.eztech.spring.bigdata.controller.HomeController;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({CassandraUnitTestExecutionListener.class})
@CassandraDataSet(value = {"simple.cql"})
@EmbeddedCassandra
@SpringBootTest
public class CassandraControllerTest {

    private Session session;

    private MockMvc mvc;


    @Before
    public void setUp() {
        Cluster cluster = Cluster.builder()
                .addContactPoints("127.0.0.1")
                .withPort(9142)
                .build();

        session = cluster.connect("cassandra_unit_keyspace");

        mvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }


    @Test
    public void should_have_started_and_execute_cql_script() throws Exception {
        ResultSet result = session.execute("select * from customer WHERE emails contains 'test@hotmail.com' ALLOW FILTERING");
        assertThat(result.iterator().next().getString("last_name"), is("zhou"));
    }


    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/cassandra/customer/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello, spring big data!")));
    }
}
