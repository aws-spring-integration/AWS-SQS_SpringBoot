package com.aston.aws.example.subscriber.listener;

import com.aston.aws.example.subscriber.service.MessageProcessingService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SqsMessageListener {

    private final MessageProcessingService service;

    @SqsListener("${aws.sqs.queue-name}")
    public void receiveMessage(String message) {
        service.process(message);
    }
}
