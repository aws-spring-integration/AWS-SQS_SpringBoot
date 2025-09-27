package com.aston.aws.sqs.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aws.sns")
public class SNSProperties {
    private String accessKey;
    private String secretKey;
    private String region = "us-east-1";
    private String topicArn;
}