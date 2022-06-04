package com.example.springtrader.client;

import com.trader.common.enums.MarketFlowType;
import com.trader.common.enums.MarketUnit;
import com.trader.common.enums.MinuteType;
import com.trader.common.utils.MinuteCandle;

import java.time.LocalDateTime;
import java.util.List;

public interface UpbitCandleClient {

    List<MinuteCandle> getMinuteCandles(MinuteType minuteType, MarketUnit marketUnit, int count, LocalDateTime localDateTime);
}
