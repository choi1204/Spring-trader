package com.example.springtrader.common.client;

import com.example.springtrader.common.domain.dto.CoinWalletDto;
import com.example.springtrader.common.properties.UpbitProperties;
import com.example.springtrader.common.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.enums.CurrencyType;
import com.example.springtrader.common.util.JwtTokenUtil;
import com.example.springtrader.common.util.UrlUtil;
import com.trader.common.enums.MinuteType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties
public class UpbitCandleClientImpl implements UpbitCandleClient {

    private final RestTemplate restTemplate;

    private final UpbitProperties upbitProperties;

    @Override
    public List<MinuteCandleDto> getMinuteCandlesDto(MinuteType minuteType, CurrencyType currencyType, int count, LocalDateTime localDateTime) {
        URI targetUrl = UrlUtil.getMinuteCandlesUrl(minuteType, currencyType, count, localDateTime, upbitProperties.getServerUrl());
        ResponseEntity<List<MinuteCandleDto>> result = restTemplate.exchange(targetUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        return result.getBody();
    }

    @Override
    public List<CoinWalletDto> getCoinWalletDto() {
        HttpHeaders authorizationHeader = JwtTokenUtil.getAuthorizationHeader(upbitProperties.getSecretKey(), upbitProperties.getAccessKey());
        HttpEntity<String> httpEntity = new HttpEntity(authorizationHeader);
        URI targetUrl = UrlUtil.getAccountUrl(upbitProperties.getServerUrl());
        ResponseEntity<List<CoinWalletDto>> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {});

        return result.getBody();
    }








}
