package com.example.springtrader.common.client;

import com.example.springtrader.common.domain.dto.UpbitCoinWallet;
import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.enums.CurrencyType;
import com.trader.common.enums.MinuteType;

import java.time.LocalDateTime;
import java.util.List;

public interface UpbitCandleClient {

    List<MinuteCandleDto> getMinuteCandlesDto(MinuteType minuteType, CurrencyType currencyType, int count, LocalDateTime localDateTime);

    List<UpbitCoinWallet> getCoinWalletDto();
}
