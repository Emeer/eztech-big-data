package com.eztech.spring.bigdata.controller.elasticsearch;

import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Elasticsearch Test Config.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.eztech.spring.bigdata.persistence.repository.elasticsearch")
public class ElasticsearchTestConfig {

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        Settings settings = Settings.builder().put("path.home", "target").build();
        return new ElasticsearchTemplate(nodeBuilder().settings(settings).local(true).node().client());
    }

}
