package com.eztech.spring.bigdata;

import com.eztech.spring.bigdata.config.RedisCacheConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Spring Big data Application
 *
 * @author Jia ZHOU
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableAutoConfiguration
@EnableCassandraRepositories(basePackages = "com.eztech.spring.bigdata.persistence.repository.cassandra")
@Import({RedisCacheConfiguration.class})
public class Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}





