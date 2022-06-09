package com.example.springtrader.crawl;

        import com.example.springtrader.common.domain.dto.MinuteCandleDto;
        import com.example.springtrader.common.enums.CurrencyType;
        import com.trader.common.enums.MinuteType;

        import java.time.LocalDateTime;
        import java.util.List;

public interface CrawlService {
    List<MinuteCandleDto> getMinuteCandlesDtoBetweenDate(MinuteType minuteType, CurrencyType currencyType, LocalDateTime startTime, LocalDateTime endTime);
}
