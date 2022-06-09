package com.example.springtrader.client;

import com.example.springtrader.common.client.UpbitCandleClient;
import com.example.springtrader.common.domain.dto.CoinWalletDto;
import com.example.springtrader.common.properties.ModeProperties;
import com.example.springtrader.common.properties.UpbitProperties;
import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.enums.CurrencyType;
import com.example.springtrader.common.service.MinuteCandleService;
import com.trader.common.enums.MinuteType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class UpbitCandleClientImplTest {

    @Autowired
    ModeProperties modeProperties;

    @Autowired
    UpbitCandleClient upbitCandleClient;

    @Autowired
    UpbitProperties upbitProperties;

    @Autowired
    MinuteCandleService minuteCandleService;


    @Test
    @DisplayName("upbit 사이트에서 캔들을 조회할 수 있다.")
    void _1() {
        List<MinuteCandleDto> minuteCandleDtoList = upbitCandleClient.getMinuteCandlesDto(MinuteType.FIVE, CurrencyType.BTC, 200, LocalDateTime.of(2022, 05, 01,0,0));
        assertThat(minuteCandleDtoList.size()).isEqualTo(200);
    }

    @Test
    @DisplayName("upbit 계좌 정보를 가져올 수 있다.")
    void _2() {
        List<CoinWalletDto> coinWalletDto = upbitCandleClient.getCoinWalletDto();
        assertThat(coinWalletDto.get(0).getCurrency()).isEqualTo("KRW");
    }




}