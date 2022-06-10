package com.example.springtrader.common.service;

import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.domain.entity.MinuteCandle;
import com.example.springtrader.common.enums.CurrencyType;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

public interface MinuteCandleService {

    void save(MinuteCandleDto minuteCandleDto);

    void saveAll(List<MinuteCandleDto> minuteCandleDtoList);

    List<MinuteCandle> getMinuteCandleByUtc(CurrencyType currencyType, LocalDateTime startTime, LocalDateTime endTime);

    List<MinuteCandle> findAll();
}
