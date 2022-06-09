package com.example.springtrader.common.util;

import com.example.springtrader.common.enums.CurrencyType;
import com.trader.common.enums.MinuteType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class UrlUtil {

    private static final String FIND_MINUTE_CANDLE_URL = "/v1/candles/minutes/{unit}";

    private static final String FIND_ACCOUNT = "/v1/accounts";


    public static URI getMinuteCandlesUrl(MinuteType minuteType, CurrencyType currencyType, int count, LocalDateTime localDateTime, String serverUrl) {
        return UriComponentsBuilder
                .fromUriString(serverUrl)
                .path(FIND_MINUTE_CANDLE_URL)
                .queryParam("market", currencyType.getMarketType())
                .queryParam("to", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime))
                .queryParam("count", count)
                .encode(StandardCharsets.UTF_8)
                .buildAndExpand(minuteType.getMinute())
                .toUri();
    }

    public static URI getAccountUrl(String serverUrl) {
        return UriComponentsBuilder
                .fromUriString(serverUrl)
                .path(FIND_ACCOUNT)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
    }
}
