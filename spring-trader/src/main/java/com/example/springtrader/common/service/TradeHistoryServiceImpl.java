package com.example.springtrader.common.service;

import com.example.springtrader.common.domain.entity.TradeHistory;
import com.example.springtrader.common.repository.TradeHistoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradeHistoryServiceImpl implements TradeHistoryService {

    private final TradeHistoryRepository tradeHistoryRepository;

    @Override
    public TradeHistory save(TradeHistory tradeHistory) {
        return tradeHistoryRepository.save(tradeHistory);
    }
}
