package com.example.springtrader.Crawler.repository;

import com.example.springtrader.Crawler.domain.entity.MinuteCandle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinuteCandleRepository extends JpaRepository<MinuteCandle, Long> {
}
