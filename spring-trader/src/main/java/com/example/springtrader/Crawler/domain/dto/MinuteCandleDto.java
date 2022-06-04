package com.example.springtrader.Crawler.domain.dto;

import com.example.springtrader.Crawler.domain.entity.MinuteCandle;
import com.example.springtrader.common.enums.MarketType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MinuteCandleDto {

    @JsonProperty("market")
    private String market;

    @JsonProperty("candle_date_time_utc")
    private LocalDateTime candleDateTimeUtc;

    @JsonProperty("candle_date_time_kst")
    private LocalDateTime candleDateTimeKst;

    @JsonProperty("opening_price")
    private Double openingPrice;

    @JsonProperty("high_price")
    private Double highPrice;

    @JsonProperty("low_price")
    private Double lowPrice;

    @JsonProperty("trade_price")
    private Double tradePrice;

    private Long timestamp;

    @JsonProperty("candle_acc_trade_price")
    private Double candleAccTradePrice;

    @JsonProperty("candle_acc_trade_volume")
    private Double candleAccTradeVolume;

    public MinuteCandle toMinuteCandle() {
        return new MinuteCandle(
                MarketType.find(market), openingPrice, highPrice, lowPrice, tradePrice, candleAccTradePrice,
                candleAccTradeVolume, candleDateTimeUtc, candleDateTimeKst, timestamp
        );
    }
}
