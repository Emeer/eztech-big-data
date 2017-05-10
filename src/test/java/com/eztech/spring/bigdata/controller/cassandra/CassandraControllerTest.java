package com.eztech.spring.bigdata.controller.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({CassandraUnitTestExecutionListener.class})
@CassandraDataSet(value = {"simple.cql"})
@EmbeddedCassandra
public class CassandraControllerTest {

    private Session session;

    @Before
    public void setUp() {
        Cluster cluster = Cluster.builder()
                .addContactPoints("127.0.0.1")
                .withPort(9142)
                .build();

        session = cluster.connect("cassandra_unit_keyspace");

    }

    @Test
    public void should_have_started_and_execute_cql_script() throws Exception {
        ResultSet result = session.execute("select * from customer WHERE first_name = 'eric' ALLOW FILTERING");
        assertThat(result.iterator().next().getString("last_name"), is("zhou"));
    }
}
