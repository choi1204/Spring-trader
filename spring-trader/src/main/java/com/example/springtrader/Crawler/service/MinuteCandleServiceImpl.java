package com.example.springtrader.Crawler.service;

import com.example.springtrader.Crawler.domain.dto.MinuteCandleDto;
import com.example.springtrader.Crawler.domain.entity.MinuteCandle;
import com.example.springtrader.Crawler.repository.MinuteCandleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MinuteCandleServiceImpl implements MinuteCandleService {

    private final MinuteCandleRepository minuteCandleRepository;

    @Override
    public void save(MinuteCandleDto minuteCandleDto) {
        MinuteCandle minuteCandle = minuteCandleDto.toMinuteCandle();
        minuteCandleRepository.save(minuteCandle);
    }

    @Override
    public void saveAll(List<MinuteCandleDto>minuteCandleDtoList) {
        List<MinuteCandle> minuteCandleList = minuteCandleDtoList.stream().map(dto -> dto.toMinuteCandle()).collect(Collectors.toList());
        minuteCandleRepository.saveAll(minuteCandleList);
    }

}
