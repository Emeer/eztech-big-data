package com.eztech.spring.bigdata.controller.elasticsearch;


import org.elasticsearch.client.transport.TransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


/**
 * Elastic Search Test Config.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.eztech.spring.bigdata.persistence.repository.elasticsearch")
public class ElasticSearchTestConfig {





}
