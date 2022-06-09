package com.example.springtrader.real.runner;

import com.example.springtrader.config.ApplicationTerminator;
import com.example.springtrader.crawl.CrawlerRunner;
import com.example.springtrader.real.RealRunner;
import com.example.springtrader.test.TestRunner;
import com.example.springtrader.common.properties.ModeProperties;
import com.example.springtrader.common.enums.ModeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class MainRunner implements ApplicationRunner {

    private final ModeProperties modeProperties;

    private final CrawlerRunner crawlerRunner;

    private final RealRunner realRunner;

    private final TestRunner testRunner;

    private final ApplicationTerminator applicationContext;

    @Override
    public void run(ApplicationArguments args) {

        try {
            ModeType modeType = ModeType.find(modeProperties.getMode());

            switch (modeType) {
                case REAL:
                    realRunner.run();
                    break;
                case TEST:
                    testRunner.run();
                    break;
                case CRAWL:
                    crawlerRunner.run();
                    break;
            }
        } catch (Exception e) {
            log.error("application.yaml 파일에서 mode를 확인해주세요.");
        } finally {
            applicationContext.exit();
        }
    }
}
