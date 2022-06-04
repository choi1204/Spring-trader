package com.example.springtrader.crawler.service;

import com.example.springtrader.crawler.domain.dto.MinuteCandleDto;

import java.util.List;

public interface MinuteCandleService {

    void save(MinuteCandleDto minuteCandleDto);

    void saveAll(List<MinuteCandleDto> minuteCandleDtoList);
}
