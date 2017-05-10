package com.eztech.spring.bigdata.persistence.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.io.Serializable;

/**
 * Cassandra entity.
 *
 * @param <I> The id type.
 */
public abstract class AbstractCassandraEntity<I extends Serializable> implements Entity<I> {

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Setter
    @PrimaryKey
    private I id;

    /**
     * {@inheritDoc}
     */
    @Override
    public I getId() {
        return id;
    }


}





