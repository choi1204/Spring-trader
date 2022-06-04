package com.example.springtrader.Crawler.service;

import com.example.springtrader.Crawler.domain.dto.MinuteCandleDto;

import java.util.List;

public interface MinuteCandleService {

    void save(MinuteCandleDto minuteCandleDto);

    void saveAll(List<MinuteCandleDto> minuteCandleDtoList);
}
