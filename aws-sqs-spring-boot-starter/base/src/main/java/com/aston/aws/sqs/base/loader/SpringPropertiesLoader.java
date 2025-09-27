package com.aston.aws.sqs.base.loader;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class SpringPropertiesLoader implements InitializingBean {
    @Value("${spring.application.name:UNKNOWN}")
    private String applicationName;

    @Value("${spring.profiles.active:UNKNOWN}")
    private String activeProfile;

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO: add maven deps of core module and then remove comments
        // ApplicationProperties.setApplicationName(applicationName);
        // ApplicationProperties.setActiveProfile(activeProfile);
    }
}
