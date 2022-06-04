package com.example.springtrader.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "mode")
@Configuration
@Getter
@Setter
public class ModeProperties {
    private String mode;
}
