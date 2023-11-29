package com.irfaan.restapi;

import com.irfaan.restapi.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({ApplicationProperties.class})
@SpringBootApplication
public class DansproApplication {

    public static void main(String[] args) {
        SpringApplication.run(DansproApplication.class, args);
    }
}
