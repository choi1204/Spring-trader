package com.example.springtrader.client;

import com.example.springtrader.domain.dto.MinuteCandleDto;
import com.example.springtrader.domain.enums.MarketType;
import com.trader.common.enums.MinuteType;

import java.time.LocalDateTime;
import java.util.List;

public interface UpbitCandleClient {

    List<MinuteCandleDto> getMinuteCandlesDto(MinuteType minuteType, MarketType marketType, int count, LocalDateTime localDateTime);
}
