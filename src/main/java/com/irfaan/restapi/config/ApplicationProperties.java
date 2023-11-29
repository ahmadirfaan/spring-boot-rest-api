package com.irfaan.restapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.irfaan.danspro")
public class ApplicationProperties {

    private String tokenKey;

    private String tokenAge;
}
