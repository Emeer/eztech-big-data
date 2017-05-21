package com.eztech.spring.bigdata.controller.cassandra;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.eztech.spring.bigdata.persistence.domain.cassandra.Customer;
import com.eztech.spring.bigdata.persistence.repository.cassandra.CustomerCassandraRepository;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.support.CassandraRepositoryFactory;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *
 */
@TestExecutionListeners({CassandraUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@CassandraDataSet(value = {"simple.cql"})
@EmbeddedCassandra
@RunWith(SpringJUnit4ClassRunner.class)
public class CassandraRepoTest {

    private Session session;

    private CustomerCassandraRepository customerCassandraRepository;


    @Before
    public void setUp() {
        Cluster cluster = Cluster.builder()
                .addContactPoints("127.0.0.1")
                .withPort(9142)
                .build();

        session = cluster.connect("cassandra_unit_keyspace");

        CassandraOperations cassandraOps = new CassandraTemplate(session);
        customerCassandraRepository = new CassandraRepositoryFactory(cassandraOps).getRepository(CustomerCassandraRepository.class);

    }


    @Test
    public void should_have_started_and_execute_cql_script() throws Exception {
        ResultSet result = session.execute("select * from customer WHERE emails contains 'test@hotmail.com' ALLOW FILTERING");
        assertThat(result.iterator().next().getString("last_name"), is("zhou"));
    }


    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setFirstName("jia");
        customer.setLastName("zhou");
        customer.setEmails(Sets.newHashSet("test1@gmail.com", "test2@hotmail.com"));
        customer.setTopScores(Lists.newArrayList(1, 2, 3));
        customer.setTodo(Maps.newHashMap(new Date(), "today"));

        Customer cratedCustomer = customerCassandraRepository.save(customer);
        assertThat(cratedCustomer.getId(), is(customer.getId()));
    }

    @Test
    public void testFindByFirstName() {
        List<Customer> customers = customerCassandraRepository.findByFirstName("eric");
        assertThat(customers.isEmpty(), is(false));
        assertThat(customers.get(0).getLastName(), is("zhou"));
    }

}
