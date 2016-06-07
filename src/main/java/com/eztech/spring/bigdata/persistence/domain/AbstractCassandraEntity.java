package com.eztech.spring.bigdata.persistence.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
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
    @PrimaryKey
    private I id;

    /**
     * {@inheritDoc}
     */
    @Override
    public I getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id The id to set.
     */
    public void setId(I id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Entity<I> other = (Entity<I>) obj;
        return Objects.equal(id, other.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.id).toString();
    }
}
