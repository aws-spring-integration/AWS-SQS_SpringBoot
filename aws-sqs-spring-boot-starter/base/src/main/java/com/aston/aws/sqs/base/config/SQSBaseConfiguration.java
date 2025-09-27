package com.aston.aws.sqs.base.config;

import com.aston.aws.sqs.base.loader.SpringPropertiesLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSBaseConfiguration {
    @Bean
    public SpringPropertiesLoader springPropertiesLoader() {
        return new SpringPropertiesLoader();
    }
}
