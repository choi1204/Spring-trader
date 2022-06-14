package com.example.springtrader.common.domain.dto;

import com.example.springtrader.common.domain.entity.CoinWallet;
import com.example.springtrader.common.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpbitCoinWallet {

    private String currency;

    private Double balance;

    private Double locked;

    @JsonProperty("avg_buy_price")
    private Double avgBuyPrice;

    @JsonProperty("avg_buy_price_modified")
    private Boolean avgBuyPriceModified;

    @JsonProperty("unit_currency")
    private String unitCurrency;

    public CoinWallet toCoinWallet() {
        CurrencyType currencyType = CurrencyType.find(currency);
        return new CoinWallet(currencyType, balance, avgBuyPrice);
    }
}
