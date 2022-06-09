package com.example.springtrader.crawl;

import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.enums.CurrencyType;
import com.trader.common.enums.MinuteType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class CrawlServiceImplTest {

    @Autowired
    CrawlService crawlService;

    @Test
    @DisplayName("2022년 5월 1일 0시 0분 부터 2022년 5월 31일 23시 59분까지 데이터를 저장할 수 있다.")
    void _1() {
        LocalDateTime startTime = LocalDateTime.of(2022, 05, 01,0,0);
        LocalDateTime endTime = LocalDateTime.of(2022, 05,31,23,59);
        List<MinuteCandleDto> minuteCandleDtoList = crawlService.getMinuteCandlesDtoBetweenDate(MinuteType.FIVE, CurrencyType.BTC, startTime, endTime);
    }

}