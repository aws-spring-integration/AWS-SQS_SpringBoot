package com.aston.aws.example.publisher;

import com.aston.aws.sqs.base.enable.EnableAwsSns;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAwsSns
@SpringBootApplication
public class AwsSnsPublisherApplication {
    public static void main(String[] args) {
        SpringApplication.run(AwsSnsPublisherApplication.class);
    }
}