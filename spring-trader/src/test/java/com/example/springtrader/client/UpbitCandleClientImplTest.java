package com.example.springtrader.client;

import com.example.springtrader.config.YamlLoadFactory;
import com.example.springtrader.config.properties.UpbitProperties;
import com.example.springtrader.enums.MarketType;
import com.trader.common.enums.MinuteType;
import com.trader.common.utils.MinuteCandle;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
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

    @Test
    @DisplayName("upbit 사이트에서 캔들을 조회할 수 있다.")
    void _1() {
        List<MinuteCandle> minuteCandles = upbitCandleClient.getMinuteCandles(MinuteType.FIVE, MarketType.KRW_BTC, 200, LocalDateTime.now());
        assertThat(minuteCandles.size()).isEqualTo(200);
    }

}