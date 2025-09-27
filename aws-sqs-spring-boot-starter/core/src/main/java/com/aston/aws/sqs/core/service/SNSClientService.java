package com.aston.aws.sqs.core.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.ListSubscriptionsByTopicRequest;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
import software.amazon.awssdk.services.sns.model.Subscription;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Wrapper around AWS SNS client with convenient operations.
 */
@Service
@RequiredArgsConstructor
public class SNSClientService {
    private final SnsClient snsClient;
    private static final Logger log = LoggerFactory.getLogger(SNSClientService.class);

    /**
     * Check if SNS client is initialized
     */
    public boolean isSNSClientAvailable() {
        return Objects.nonNull(snsClient) && Objects.nonNull(snsClient.listTopics());
    }

    /**
     * Publish a message to a topic
     */
    public String publish(String topicArn, String message) {
        PublishResponse response = snsClient.publish(
                PublishRequest.builder()
                        .topicArn(topicArn)
                        .message(message)
                        .build()
        );
        log.info("Published message to SNS topic {} with ID {}", topicArn, response.messageId());
        return response.messageId();
    }

    /**
     * Subscribe endpoint (email, SQS, Lambda, etc.) to topic
     */
    public String subscribe(String topicArn, String protocol, String endpoint) {
        SubscribeResponse response = snsClient.subscribe(
                SubscribeRequest.builder()
                        .topicArn(topicArn)
                        .protocol(protocol)
                        .endpoint(endpoint)
                        .build()
        );
        log.info("Created subscription on topic {} for endpoint {}", topicArn, endpoint);
        return response.subscriptionArn();
    }

    /**
     * List all subscriptions for a topic
     */
    public List<String> listSubscriptions(String topicArn) {
        return snsClient.listSubscriptionsByTopic(
                        ListSubscriptionsByTopicRequest.builder()
                                .topicArn(topicArn)
                                .build()
                ).subscriptions().stream()
                .map(Subscription::endpoint)
                .collect(Collectors.toList());
    }
}
