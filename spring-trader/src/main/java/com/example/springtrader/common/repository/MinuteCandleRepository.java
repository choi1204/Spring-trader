package com.example.springtrader.common.repository;

import com.example.springtrader.common.domain.entity.MinuteCandle;
import com.example.springtrader.common.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MinuteCandleRepository extends JpaRepository<MinuteCandle, Long> {
    List<MinuteCandle> findByCurrencyTypeAndCandleDateTimeUtcBetween(CurrencyType currencyType, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
