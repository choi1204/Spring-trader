package com.example.springtrader.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TradeType {
    BID("bid"), ASK("ask");

    final String type;
}
