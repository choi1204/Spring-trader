package com.example.springtrader.test.bid;

public class RSIBidStrategy implements BidStrategy {

    private final double RSI_BID_VALUE = 30.d;

    @Override
    public boolean isBid() {
        return false;
    }
}
