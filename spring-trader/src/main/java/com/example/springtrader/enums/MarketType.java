package com.example.springtrader.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MarketType implements EnumFindable {
    KRW_BTC("KRW-BTC");

    private String type;

    public static MarketType find(String type) {
        return EnumFindable.find(type, MarketType.values());
    }
}
