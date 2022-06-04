package com.example.springtrader.config;

import org.springframework.boot.ExitCodeGenerator;

public class SpringTraderExitCodeGenerator implements ExitCodeGenerator {
    @Override
    public int getExitCode() {
        return 42;
    }
}
