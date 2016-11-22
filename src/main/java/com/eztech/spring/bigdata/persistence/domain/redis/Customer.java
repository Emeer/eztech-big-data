package com.eztech.spring.bigdata.persistence.domain.redis;

import com.eztech.spring.bigdata.persistence.domain.AbstractRedisEntity;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.data.redis.core.RedisHash;

/**
 * 
 */
@RedisHash("customer")
public class Customer extends AbstractRedisEntity<String> {

}

