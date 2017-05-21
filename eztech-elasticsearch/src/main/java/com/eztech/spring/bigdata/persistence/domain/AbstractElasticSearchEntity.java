package com.eztech.spring.bigdata.persistence.domain;

import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * ElasticSearch entity.
 *
 * @param <I> The id type.
 */
public abstract class AbstractElasticSearchEntity<I extends Serializable> implements Entity<I> {

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Setter
    @Id
    private I id;

    /**
     * {@inheritDoc}
     */
    @Override
    public I getId() {
        return id;
    }

}





