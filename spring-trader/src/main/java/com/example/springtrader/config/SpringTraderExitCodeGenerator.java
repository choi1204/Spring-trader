package com.example.springtrader.config;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class SpringTraderExitCodeGenerator implements ExitCodeGenerator {
    @Override
    public int getExitCode() {
        return 42;
    }
}
