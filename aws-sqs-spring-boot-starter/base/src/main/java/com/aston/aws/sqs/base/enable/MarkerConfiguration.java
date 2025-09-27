package com.aston.aws.sqs.base.enable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarkerConfiguration {
    @Bean
    public Marker snsClientServiceMarkerBean() {
        return new Marker();
    }

    public class Marker {
    }
}