package com.example.springtrader.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CurrencyType implements EnumFindable {
    KRW("KRW"), BTC("BTC"), DOGE("DOGE"), BTT("BTT");

    private final String type;

    public String getMarketType() {
        return "KRW-"+ type;
    }
    public static CurrencyType findMarket(String marketType) {
        String currencyType = marketType.substring(4);
        return find(currencyType);
    }
    public static CurrencyType find(String type) {
        return EnumFindable.find(type, CurrencyType.values());
    }
}
