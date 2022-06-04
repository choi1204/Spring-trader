package com.example.springtrader.service;

import com.example.springtrader.domain.dto.MinuteCandleDto;

import java.util.List;

public interface MinuteCandleService {

    void save(MinuteCandleDto minuteCandleDto);

    void saveAll(List<MinuteCandleDto> minuteCandleDtoList);
}
