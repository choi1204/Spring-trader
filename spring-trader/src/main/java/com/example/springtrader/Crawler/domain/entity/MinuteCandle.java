package com.example.springtrader.Crawler.domain.entity;

import com.example.springtrader.common.enums.MarketType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class MinuteCandle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MarketType marketType;

    private Double openingPrice;

    private Double highPrice;

    private Double lowPrice;

    private Double tradePrice;

    private Double candleAccTradePrice;

    private Double candleAccTradeVolume;

    private LocalDateTime candleDateTimeUtc;

    private LocalDateTime candleDateTimeKst;

    private Long timestamp;


    public MinuteCandle(MarketType marketType, Double openingPrice, Double highPrice, Double lowPrice, Double tradePrice, Double candleAccTradePrice, Double candleAccTradeVolume, LocalDateTime candleDateTimeUtc, LocalDateTime candleDateTimeKst, Long timestamp) {
        this.marketType = marketType;
        this.openingPrice = openingPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.tradePrice = tradePrice;
        this.candleAccTradePrice = candleAccTradePrice;
        this.candleAccTradeVolume = candleAccTradeVolume;
        this.candleDateTimeUtc = candleDateTimeUtc;
        this.candleDateTimeKst = candleDateTimeKst;
        this.timestamp = timestamp;
    }
}
