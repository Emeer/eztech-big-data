package com.eztech.spring.bigdata.persistence.domain.cassandra;

import com.eztech.spring.bigdata.persistence.domain.AbstractCassandraEntity;
import com.google.common.base.MoreObjects;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table
public class Customer extends AbstractCassandraEntity<UUID> {


    private String firstName;

    private String lastName;

    public Customer() {
    }

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
