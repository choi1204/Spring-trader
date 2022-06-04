package com.example.springtrader.crawler.repository;

import com.example.springtrader.crawler.domain.entity.MinuteCandle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinuteCandleRepository extends JpaRepository<MinuteCandle, Long> {
}
