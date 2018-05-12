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

import java.io.IOException;

@Configuration
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
