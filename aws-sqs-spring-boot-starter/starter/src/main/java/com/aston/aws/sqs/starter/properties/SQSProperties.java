package com.aston.aws.sqs.starter.properties;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aws.sqs")
public class SQSProperties {
    /**
     * Enable or disable SQS integration.
     * Default: true
     */
    private boolean enable = true;

    /**
     * AWS access key for SQS client.
     */
    private String accessKey;

    /**
     * AWS secret key for SQS client.
     */
    private String secretKey;

    /**
     * AWS region (e.g., us-east-1).
     */
    private String region = "us-east-1";

    /**
     * Default queue name for publishing/consuming messages.
     */
    private String queueName;

    /**
     * Maximum number of messages to poll in a single request.
     * Default: 10 (AWS limit).
     */
    private int maxMessages = 10;

    /**
     * Long polling wait time (seconds).
     * Default: 20
     */
    private int waitTimeSeconds = 20;

    /**
     * Visibility timeout (seconds).
     * Default: 30
     */
    private int visibilityTimeout = 30;
}
