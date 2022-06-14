package com.example.springtrader.common.repository;

import com.example.springtrader.common.domain.entity.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {
}
