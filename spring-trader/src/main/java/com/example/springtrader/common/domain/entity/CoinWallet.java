package com.example.springtrader.common.domain.entity;

import com.example.springtrader.common.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
public class CoinWallet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    //매수금액
    private Double totalBidPrice;

    //보유 수량
    private Double balance;

    //매수 평균가
    private Double avgBuyPrice;

    public CoinWallet(CurrencyType currencyType, Double balance, Double avgBuyPrice) {
        this.currencyType = currencyType;
        this.totalBidPrice = avgBuyPrice * balance;
        this.balance = balance;
        this.avgBuyPrice = avgBuyPrice;
    }
}
