package com.example.springtrader.runner;

import com.example.springtrader.config.SpringTraderExitCodeGenerator;
import com.example.springtrader.crawler.CrawlerRunner;
import com.example.springtrader.real.RealRunner;
import com.example.springtrader.test.TestRunner;
import com.example.springtrader.common.properties.ModeProperties;
import com.example.springtrader.common.enums.ModeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MainRunner implements ApplicationRunner {

    private final ModeProperties modeProperties;

    private final CrawlerRunner crawlerRunner;

    private final RealRunner realRunner;

    private final TestRunner testRunner;

    private final ApplicationContext applicationContext;

    private final SpringTraderExitCodeGenerator exitCodeGenerator;

    @Override
    public void run(ApplicationArguments args)  {
        ModeType modeType = ModeType.find(modeProperties.getMode());

        try {
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
            SpringApplication.exit(applicationContext, exitCodeGenerator);
        }
    }
}
