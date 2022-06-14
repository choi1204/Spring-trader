package com.example.springtrader.test.ask;

public class RSIAskStrategy implements AskStrategy {

    private final double RSI_ASK_VALUE = 70.d;
    @Override
    public boolean isAsk() {
        return false;
    }
}
