package com.ledgityapp.appapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.auth-service")
public class ConfigUtils {

    @Value("${registrationUrl}")
    private String registrationUrl;
    @Value("${loginUrl}")
    private String loginUrl;
    @Value("${validateUrl}")
    private String validateUrl;
    @Value("${basicAuth}")
    private String basicAuth;
}
