package com.eztech.spring.bigdata;

import com.eztech.spring.bigdata.config.HadoopConfig;
import com.eztech.spring.bigdata.config.RedisCacheConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Spring Big data Application
 *
 * @author Jia ZHOU
 */
@SpringBootApplication
@Import({HadoopConfig.class, RedisCacheConfiguration.class})
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
