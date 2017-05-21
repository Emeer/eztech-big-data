package com.eztech.spring.bigdata.persistence.domain.elasticsearch;

import com.eztech.spring.bigdata.persistence.domain.AbstractElasticSearchEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Customer.
 */
@Data
@NoArgsConstructor
@Document(indexName = "customer", type = "customer", shards = 1, replicas = 0, refreshInterval = "-1")
public class Customer extends AbstractElasticSearchEntity<String> {


    private String firstName;


    private String lastName;


    private Set<String> emails;


    private List<Integer> topScores;


    private Map<Date, String> todo;


}
