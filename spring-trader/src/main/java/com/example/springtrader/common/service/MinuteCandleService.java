package com.example.springtrader.common.service;

import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.domain.entity.MinuteCandle;
import com.example.springtrader.common.enums.CurrencyType;

import java.time.LocalDateTime;
import java.util.List;

public interface MinuteCandleService {

    void save(MinuteCandleDto minuteCandleDto);

    void saveAll(List<MinuteCandleDto> minuteCandleDtoList);

    List<MinuteCandle> getMinuteCandleByUtcBetweenDate(CurrencyType currencyType, LocalDateTime startTime, LocalDateTime endTime);

    List<MinuteCandle> getMinuteCandleByUtcBeforeTime(CurrencyType currencyType, LocalDateTime targetTime);

    MinuteCandle getMinuteCandleByUtc(CurrencyType currencyType, LocalDateTime utcTime);

    List<MinuteCandle> findAll();
}
