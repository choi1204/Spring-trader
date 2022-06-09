package com.example.springtrader.crawl;

import com.example.springtrader.common.client.UpbitCandleClient;
import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.enums.CurrencyType;
import com.example.springtrader.common.service.MinuteCandleService;
import com.example.springtrader.common.util.ThreadUtil;
import com.trader.common.enums.MinuteType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlServiceImpl implements CrawlService {

    private final UpbitCandleClient upbitCandleClient;

    private final MinuteCandleService minuteCandleService;

    private final int SLEEP_TIME = 80;

    private final int MAX_REQUEST = 200;

    @Override
    public List<MinuteCandleDto> getMinuteCandlesDtoBetweenDate(MinuteType minuteType, CurrencyType currencyType, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime currentTime = endTime.minusMinutes(minuteType.getMinute());
        List<MinuteCandleDto> minuteCandleDtoList = new ArrayList<>();

        while (startTime.isBefore(currentTime)) {
            int requestCount = getRequestCount(startTime, currentTime, minuteType);
            minuteCandleDtoList.addAll(upbitCandleClient.getMinuteCandlesDto(minuteType, currencyType, requestCount, currentTime));
            currentTime = minuteCandleDtoList.get(minuteCandleDtoList.size() - 1).getCandleDateTimeUtc();
            ThreadUtil.threadSleep(SLEEP_TIME);
        }
        minuteCandleService.saveAll(minuteCandleDtoList);
        return minuteCandleDtoList;
    }

    private int getRequestCount(LocalDateTime startTime,LocalDateTime currentTime, MinuteType minuteType) {
        int count = (int) ChronoUnit.MINUTES.between(startTime, currentTime) / minuteType.getMinute();

        return Math.min(count, MAX_REQUEST);
    }

}
