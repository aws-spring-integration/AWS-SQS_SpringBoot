package com.aston.aws.sqs.base.loader;

import com.aston.aws.sqs.core.properties.ApplicationProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class SpringPropertiesLoader implements InitializingBean {
    @Value("${spring.application.name:UNKNOWN}")
    private String applicationName;

    @Value("${spring.profiles.active:UNKNOWN}")
    private String activeProfile;

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationProperties.setApplicationName(applicationName);
        ApplicationProperties.setActiveProfile(activeProfile);
    }
}
