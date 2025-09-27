package com.aston.aws.sqs.starter.autoconfigure;

import com.aston.aws.sqs.base.config.AWSBaseConfiguration;
import com.aston.aws.sqs.base.enable.MarkerConfiguration;
import com.aston.aws.sqs.core.service.SQSClientService;
import com.aston.aws.sqs.starter.properties.SQSProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
@ConditionalOnBean(MarkerConfiguration.Marker.class)
@Import(AWSBaseConfiguration.class)
@AutoConfigureAfter(AWSBaseConfiguration.class)
@EnableConfigurationProperties(SQSProperties.class)
@ConditionalOnProperty(prefix = "aws.sqs", name = "enable", havingValue = "true",
        matchIfMissing = true)
public class SQSAutoConfiguration {
    @Bean
    public SqsClient sqsClient(SQSProperties properties) {
        return SqsClient.builder()
                .region(Region.of(properties.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(properties.getAccessKey(),
                                        properties.getSecretKey())
                        )
                )
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public SQSClientService sqsClientService(SqsClient sqsClient) {
        return new SQSClientService(sqsClient);
    }
}
