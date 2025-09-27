package com.aston.aws.sqs.core.properties;

import lombok.Getter;
import lombok.Setter;

public class ApplicationProperties {
    /**
     * App name
     */
    @Getter
    @Setter
    private static String applicationName;

    /**
     * Spring App active profile
     */
    @Getter
    @Setter
    private static String activeProfile;
}
