package com.example.springtrader;

import com.trader.common.enums.MarketUnit;
import com.trader.common.enums.MinuteType;
import com.trader.common.utils.MinuteCandle;
import lombok.extern.slf4j.Slf4j;
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
class SpringTraderApplicationTests {

    @Autowired
    UpbitCandleClient upbitCandleClient;

    @Autowired
    UpbitProperties upbitProperties;

    @Test
    void contextLoads() {

        List<MinuteCandle> minuteCandles = upbitCandleClient.getMinuteCandles(MinuteType.FIVE, MarketUnit.KRW, 200, LocalDateTime.now());
        assertThat(minuteCandles.size()).isEqualTo(200);
    }

}
