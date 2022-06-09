package com.example.springtrader.test;

import com.example.springtrader.common.client.UpbitCandleClient;
import com.example.springtrader.common.client.UpbitCandleClientImpl;
import com.example.springtrader.common.domain.entity.MinuteCandle;
import com.example.springtrader.common.service.MinuteCandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UpbitCandleClient upbitCandleClient;

    private final MinuteCandleService minuteCandleService;

    public void test() {
        List<MinuteCandle> minuteCandleList = minuteCandleService.findAll();
        Double money = 1000000.0;
        for(MinuteCandle minuteCandle : minuteCandleList) {
            if (isBid()) {
                //bid
            } else if(isAsk()) {
                //ask
            }
        }
    }

    private boolean isAsk() {
        return true;
    }

    private boolean isBid() {
        return false;
    }
}
