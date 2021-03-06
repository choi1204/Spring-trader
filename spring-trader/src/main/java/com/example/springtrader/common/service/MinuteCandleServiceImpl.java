package com.example.springtrader.common.service;

import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.domain.entity.MinuteCandle;
import com.example.springtrader.common.enums.CurrencyType;
import com.example.springtrader.common.repository.MinuteCandleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Transactional
    public void saveAll(List<MinuteCandleDto>minuteCandleDtoList) {
        List<MinuteCandle> minuteCandleList = minuteCandleDtoList.stream().map(dto -> dto.toMinuteCandle()).collect(Collectors.toList());
        minuteCandleRepository.saveAll(minuteCandleList);
    }

    @Override
    public List<MinuteCandle> getMinuteCandleByUtcBetweenDate(CurrencyType currencyType, LocalDateTime startTime, LocalDateTime endTime) {
        return minuteCandleRepository.findByCurrencyTypeAndCandleDateTimeUtcBetween(currencyType, startTime, endTime);
    }

    @Override
    public List<MinuteCandle> getMinuteCandleByUtcBeforeTime(CurrencyType currencyType, LocalDateTime targetTime) {
        return minuteCandleRepository.findByCurrencyTypeAndCandleDateTimeUtcBefore(currencyType, targetTime);
    }

    @Override
    public MinuteCandle getMinuteCandleByUtc(CurrencyType currencyType, LocalDateTime utcTime) {
        return minuteCandleRepository.findByCurrencyTypeAndCandleDateTimeUtc(currencyType, utcTime);
    }


    @Override
    public List<MinuteCandle> findAll() {
        return minuteCandleRepository.findAll();
    }


}
