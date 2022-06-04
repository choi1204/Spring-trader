package com.example.springtrader.common.client;

import com.example.springtrader.Crawler.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.enums.MarketType;
import com.trader.common.enums.MinuteType;

import java.time.LocalDateTime;
import java.util.List;

public interface UpbitCandleClient {

    List<MinuteCandleDto> getMinuteCandlesDto(MinuteType minuteType, MarketType marketType, int count, LocalDateTime localDateTime);

    List<MinuteCandleDto> getMinuteCandlesDtoBetweenDate(MinuteType minuteType, MarketType marketType, LocalDateTime startTime, LocalDateTime endTime);
}
