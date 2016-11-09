package com.eztech.spring.bigdata.persistence.domain.cassandra;

import com.eztech.spring.bigdata.persistence.domain.AbstractCassandraEntity;
import com.google.common.base.MoreObjects;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * The type Customer.
 */
@Table
public class Customer extends AbstractCassandraEntity<UUID> {


    private String firstName;

    private String lastName;

    /**
     * Instantiates a new Customer.
     */
    public Customer() {
    }

    /**
     * Instantiates a new Customer.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Customer(UUID id, String firstName, String lastName) {
        setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(getId()).addValue(this.firstName).addValue(this.lastName).toString();
    }

}
