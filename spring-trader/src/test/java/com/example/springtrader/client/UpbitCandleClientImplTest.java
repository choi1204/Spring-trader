package com.example.springtrader.client;

import com.example.springtrader.config.YamlLoadFactory;
import com.example.springtrader.config.properties.UpbitProperties;
import com.example.springtrader.domain.dto.MinuteCandleDto;
import com.example.springtrader.domain.entity.MinuteCandle;
import com.example.springtrader.domain.enums.MarketType;
import com.example.springtrader.service.MinuteCandleService;
import com.trader.common.enums.MinuteType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@PropertySource(
        value = {"classpath:application.yaml"},
        factory = YamlLoadFactory.class
)
class UpbitCandleClientImplTest {

    @Autowired
    UpbitCandleClient upbitCandleClient;

    @Autowired
    UpbitProperties upbitProperties;

    @Autowired
    MinuteCandleService minuteCandleService;

    @Test
    @DisplayName("upbit 사이트에서 캔들을 조회할 수 있다.")
    void _1() {
        List<MinuteCandleDto> minuteCandleDtoList = upbitCandleClient.getMinuteCandlesDto(MinuteType.FIVE, MarketType.KRW_BTC, 200, LocalDateTime.of(2022, 05, 01,0,0));
        assertThat(minuteCandleDtoList.size()).isEqualTo(200);

    }

    @Test
    void _12() {
        LocalDateTime startTime = LocalDateTime.of(2022, 05, 01,0,0);
        LocalDateTime endTime = LocalDateTime.of(2022, 06,1,0,00);

        long between = ChronoUnit.MINUTES.between(startTime, endTime);
        int a = (int)between / 5;
        LocalDateTime currentTime = startTime;

        for (int i = 0; i < a; i++) {
            currentTime = currentTime.minusMinutes(5 * 200);
        }
        System.out.println(currentTime);
    }
    @Test
    @DisplayName("2022년 5월 1일 0시 0분 부터 2022년 5월 31일 23시 59분까지 데이터를 저장할 수 있다.")
    void _2() {
        LocalDateTime startTime = LocalDateTime.of(2022, 05, 01,0,0);
        LocalDateTime endTime = LocalDateTime.of(2022, 05,31,23,59);
        List<MinuteCandleDto> minuteCandleDtoList = upbitCandleClient.getMinuteCandlesDtoBetweenDate(MinuteType.FIVE, MarketType.KRW_BTC, startTime, endTime);
        minuteCandleService.saveAll(minuteCandleDtoList);
    }

}