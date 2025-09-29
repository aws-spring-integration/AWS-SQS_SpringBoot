package com.aston.aws.example.subscriber;

import com.aston.aws.sqs.base.enable.EnableAwsSqs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAwsSqs
@EnableScheduling
@SpringBootApplication
public class SqsConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SqsConsumerApplication.class, args);
    }
}
