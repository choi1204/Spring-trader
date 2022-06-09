package com.example.springtrader.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationTerminator {

    private final ApplicationContext applicationContext;

    private final SpringTraderExitCodeGenerator exitCodeGenerator;

    public void exit() {
        SpringApplication.exit(applicationContext, exitCodeGenerator);
    }
}
