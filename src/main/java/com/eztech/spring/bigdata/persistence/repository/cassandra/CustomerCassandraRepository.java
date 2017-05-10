package com.eztech.spring.bigdata.persistence.repository.cassandra;

import com.eztech.spring.bigdata.persistence.domain.cassandra.Customer;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerCassandraRepository extends CrudRepository<Customer, UUID> {

    @Query("Select * from customer where first_name=?0 ALLOW FILTERING")
    public List<Customer> findByFirstName(String firstName);

    @Query("Select * from customer where last_name=?0 ALLOW FILTERING")
    public List<Customer> findByLastName(String lastName);

}
