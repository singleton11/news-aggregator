package com.singleton.newsaggregator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.singleton.newsaggregator.service.AggregatorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@Configuration
@EnableScheduling
@EnableAsync
@EnableRetry
public class AppConfig {

    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    private final ApplicationContext applicationContext;

    @Autowired
    public AppConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    AggregatorConfig aggregatorConfig() {
        var mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper
                    .readValue(applicationContext
                                       .getResource("classpath:aggregator-config.yml").getFile(),
                               AggregatorConfig.class);
        } catch (IOException e) {
            logger.error("YAML Config not found");
            return null;
        }
    }
}
