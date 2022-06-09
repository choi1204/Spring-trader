package com.example.springtrader.common.repository;

import com.example.springtrader.common.domain.entity.MinuteCandle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinuteCandleRepository extends JpaRepository<MinuteCandle, Long> {
}
