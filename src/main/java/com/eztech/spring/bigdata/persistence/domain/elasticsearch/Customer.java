package com.eztech.spring.bigdata.persistence.domain.elasticsearch;

import com.eztech.spring.bigdata.persistence.domain.AbstractElasticSearchEntity;
import com.google.common.base.MoreObjects;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "customer", type = "customer", shards = 1, replicas = 0, refreshInterval = "-1")
public class Customer extends AbstractElasticSearchEntity<String> {


    private String firstName;

    private String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(getId()).addValue(this.firstName).addValue(this.lastName).toString();
    }

}
