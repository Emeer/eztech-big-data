package com.eztech.spring.bigdata.controller.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ CassandraUnitTestExecutionListener.class })
@CassandraDataSet(value = { "simple.cql" })
@EmbeddedCassandra
public class CassandraControllerTest {

    @Test
    public void should_have_started_and_execute_cql_script() throws Exception {
        Cluster cluster = Cluster.builder()
                .addContactPoints("127.0.0.1")
                .withPort(9142)
                .build();
        Session session = cluster.connect("cassandra_unit_keyspace");

        ResultSet result = session.execute("select * from mytable WHERE id='myKey01'");
        assertThat(result.iterator().next().getString("value"), is("myValue01"));
    }
}
