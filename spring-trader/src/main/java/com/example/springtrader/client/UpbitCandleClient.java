package com.example.springtrader.client;

import com.example.springtrader.domain.enums.MarketType;
import com.trader.common.enums.MinuteType;
import com.trader.common.utils.MinuteCandle;

import java.time.LocalDateTime;
import java.util.List;

public interface UpbitCandleClient {

    List<MinuteCandle> getMinuteCandles(MinuteType minuteType, MarketType marketType, int count, LocalDateTime localDateTime);
}
