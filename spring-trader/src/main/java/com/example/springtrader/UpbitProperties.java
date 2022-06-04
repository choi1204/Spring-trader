package com.example.springtrader;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "upbit")
@Configuration
@Getter
@Setter
public class UpbitProperties {
    private String accessKey;
    private String secretKey;
    private String serverUrl;
}
