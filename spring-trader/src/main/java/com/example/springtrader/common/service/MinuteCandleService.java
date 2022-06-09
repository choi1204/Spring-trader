package com.example.springtrader.common.service;

import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.domain.entity.MinuteCandle;

import java.util.List;

public interface MinuteCandleService {

    void save(MinuteCandleDto minuteCandleDto);

    void saveAll(List<MinuteCandleDto> minuteCandleDtoList);

    List<MinuteCandle> findAll();
}
