package com.eztech.spring.bigdata.persistence.domain;

import java.io.Serializable;

/**
 * Entity.
 *
 * @param <I> The id type.
 */
public interface Entity<I extends Serializable> extends Serializable {

    /**
     * Returns the id.
     *
     * @return The id.
     */
    I getId();

}
