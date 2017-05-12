package com.eztech.spring.bigdata.controller.cassandra;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.eztech.spring.bigdata.persistence.repository.cassandra.CustomerCassandraRepository;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@TestExecutionListeners(listeners = {CassandraUnitDependencyInjectionTestExecutionListener.class})
@CassandraDataSet(value = {"simple.cql"})
@CassandraUnit
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CassandraRepoTest {

    private Session session;

    @Autowired
    private CustomerCassandraRepository customerCassandraRepository;


    @Before
    public void setUp() {
        Cluster cluster = Cluster.builder()
                .addContactPoints("127.0.0.1")
                .withPort(9142)
                .build();

        session = cluster.connect("cassandra_unit_keyspace");
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void should_have_started_and_execute_cql_script() throws Exception {
        ResultSet result = session.execute("select * from customer WHERE emails contains 'test@hotmail.com' ALLOW FILTERING");
        assertThat(result.iterator().next().getString("last_name"), is("zhou"));
    }
}
