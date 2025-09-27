package com.aston.aws.sqs.core.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.List;
import java.util.Objects;

/**
 * Wrapper around AWS SQS client with convenient operations.
 */
@Service
@RequiredArgsConstructor
public class SQSClientService {
    private final SqsClient sqsClient;
    private static final Logger log = LoggerFactory.getLogger(SQSClientService.class);

    /** Check if SQS client is initialized */
    public boolean isSQSClientAvailable() {
        return Objects.nonNull(sqsClient) && Objects.nonNull(sqsClient.listQueues());
    }

    /** Send a message to SQS queue */
    public String sendMessage(String queueUrl, String message) {
        SendMessageResponse response = sqsClient.sendMessage(
                SendMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .messageBody(message)
                        .build()
        );
        log.info("Sent message to SQS queue {} with ID {}", queueUrl, response.messageId());
        return response.messageId();
    }

    /** Receive messages from SQS queue */
    public List<Message> receiveMessages(String queueUrl, int maxMessages) {
        ReceiveMessageResponse response = sqsClient.receiveMessage(
                ReceiveMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .maxNumberOfMessages(maxMessages)
                        .waitTimeSeconds(10)
                        .build()
        );
        return response.messages();
    }

    /** Delete message from SQS queue */
    public void deleteMessage(String queueUrl, String receiptHandle) {
        sqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build());
        log.info("Deleted message from queue {}", queueUrl);
    }

    /** List all queues */
    public List<String> listQueues() {
        return sqsClient.listQueues().queueUrls();
    }
}
