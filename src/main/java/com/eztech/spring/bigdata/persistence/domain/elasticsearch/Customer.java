package com.eztech.spring.bigdata.persistence.domain.elasticsearch;

import com.eztech.spring.bigdata.persistence.domain.AbstractElasticSearchEntity;
import com.google.common.base.MoreObjects;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * The type Customer.
 */
@Document(indexName = "customer", type = "customer", shards = 1, replicas = 0, refreshInterval = "-1")
public class Customer extends AbstractElasticSearchEntity<String> {



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
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(getId()).addValue(this.firstName).addValue(this.lastName).toString();
    }

}
