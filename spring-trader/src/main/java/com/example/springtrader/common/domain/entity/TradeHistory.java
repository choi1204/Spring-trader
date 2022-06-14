package com.example.springtrader.common.domain.entity;

import com.example.springtrader.common.enums.CurrencyType;
import com.example.springtrader.common.enums.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class TradeHistory {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    private Double balance;

    private Double avgPrice;

    private Double totalPrice;

    private LocalDateTime tradeTime;

    private TradeHistory(TradeType tradeType, CurrencyType currencyType, Double balance, Double avgPrice, LocalDateTime tradeTime) {
        this.tradeType = tradeType;
        this.currencyType = currencyType;
        this.balance = balance;
        this.avgPrice = avgPrice;
        this.totalPrice = balance * totalPrice;
        this.tradeTime = tradeTime;
    }

    public static TradeHistory bidOf(CurrencyType currencyType, Double balance, Double avgPrice, LocalDateTime tradeTime) {
        return new TradeHistory(TradeType.BID, currencyType, balance, avgPrice, tradeTime);
    }

    public static TradeHistory askOf(CurrencyType currencyType, Double balance, Double avgPrice, LocalDateTime tradeTime) {
        return new TradeHistory(TradeType.ASK, currencyType, balance, avgPrice, tradeTime);
    }
}
