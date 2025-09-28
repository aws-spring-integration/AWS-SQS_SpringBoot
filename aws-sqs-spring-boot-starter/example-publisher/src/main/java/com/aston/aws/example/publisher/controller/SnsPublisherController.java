package com.aston.aws.example.publisher.controller;

import com.aston.aws.sqs.core.service.SNSClientService;
import com.aston.aws.sqs.starter.properties.SNSProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sns")
@RequiredArgsConstructor
public class SnsPublisherController {
    private final SNSClientService snsClientService;
    private final SNSProperties snsProperties;

    /**
     * Publish a message to the configured SNS topic.
     */
    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestParam("message") String message) {
        String messageId = snsClientService.publish(snsProperties.getTopicArn(), message);
        return ResponseEntity.ok("Message published with ID: " + messageId);
    }

    /**
     * Subscribe an endpoint (e.g., SQS queue, email) to the configured SNS topic.
     */
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(
            @RequestParam("protocol") String protocol,
            @RequestParam("endpoint") String endpoint) {
        String subscriptionArn = snsClientService.subscribe(
                snsProperties.getTopicArn(),
                protocol,
                endpoint
        );
        return ResponseEntity.ok("Subscription created: " + subscriptionArn);
    }

    /**
     * List all subscriptions for the configured SNS topic.
     */
    @GetMapping("/subscriptions")
    public ResponseEntity<List<String>> listSubscriptions() {
        List<String> subscriptions =
                snsClientService.listSubscriptions(snsProperties.getTopicArn());
        return ResponseEntity.ok(subscriptions);
    }
}