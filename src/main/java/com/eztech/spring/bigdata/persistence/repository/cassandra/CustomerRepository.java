package com.eztech.spring.bigdata.persistence.repository.cassandra;

import com.eztech.spring.bigdata.persistence.domain.cassandra.Customer;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    @Query("Select * from customer where firstname=?0")
    public Customer findByFirstName(String firstName);

    @Query("Select * from customer where lastname=?0")
    public List<Customer> findByLastName(String lastName);

}
