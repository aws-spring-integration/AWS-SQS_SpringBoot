package com.aston.aws.example.subscriber.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageProcessingService {
    public void process(String message) {
        log.info("Processing message: {}", message);
    }
}
