package com.eztech.spring.bigdata.persistence.domain.cassandra;

import com.eztech.spring.bigdata.persistence.domain.AbstractCassandraEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.Table;

import java.util.*;

/**
 * The type Customer.
 */
@Data
@NoArgsConstructor
@Table("customer")
public class Customer extends AbstractCassandraEntity<UUID> {


    @Column("first_name")
    private String firstName;


    @Column("last_name")
    private String lastName;


    @Column("emails")
    private Set<String> emails;


    @Column("top_scores")
    private List<Integer> topScores;


    @Column("todo")
    private Map<Date, String> todo;


}
