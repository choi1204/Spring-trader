package com.example.springtrader.common.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springtrader.common.properties.UpbitProperties;
import com.example.springtrader.Crawler.domain.dto.MinuteCandleDto;
import com.example.springtrader.common.enums.MarketType;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties
public class UpbitCandleClientImpl implements UpbitCandleClient {

    private final RestTemplate restTemplate;

    private final UpbitProperties upbitProperties;

    private final int MAX_REQUEST = 200;

    private final int FIVE_MIN = 5;

    private final int SLEEP_TIME = 80;


    @Override
    public List<MinuteCandleDto> getMinuteCandlesDto(MinuteType minuteType, MarketType marketType, int count, LocalDateTime localDateTime) {

        HttpHeaders authorizationHeader = getAuthorizationHeader(getJwtToken());
        HttpEntity<String> httpEntity = new HttpEntity<>(authorizationHeader);

        URI targetUrl = getMinuteCandlesUrl(minuteType, marketType, count, localDateTime);

        ResponseEntity<List<MinuteCandleDto>> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {});
        return result.getBody();
    }

    @Override
    public List<MinuteCandleDto> getMinuteCandlesDtoBetweenDate(MinuteType minuteType, MarketType marketType, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime currentTime = endTime;
        List<MinuteCandleDto> minuteCandleDtoList = new ArrayList<>();

        while (checkStartTime(startTime, currentTime)) {
            minuteCandleDtoList.addAll(getMinuteCandlesDto(minuteType, marketType, MAX_REQUEST, currentTime));
            currentTime = currentTime.minusMinutes(5 * MAX_REQUEST);
            threadSleep(SLEEP_TIME);
        }

        int between = (int)ChronoUnit.MINUTES.between(startTime, currentTime);
        if (between != 0 && between > 0) {
            minuteCandleDtoList.addAll(getMinuteCandlesDto(minuteType, marketType, between / 5, currentTime));
        }

        return minuteCandleDtoList;
    }

    private boolean checkStartTime(LocalDateTime startTime, LocalDateTime currentTime) {
        long between = ChronoUnit.MINUTES.between(startTime, currentTime);

        if (!startTime.isBefore(currentTime)) {
            return false;
        } else if (between <= MAX_REQUEST * FIVE_MIN) {
            return false;
        } else {
            return true;
        }
    }

    private void threadSleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private URI getMinuteCandlesUrl(MinuteType minuteType, MarketType marketType, int count, LocalDateTime localDateTime) {
        return UriComponentsBuilder
                .fromUriString(upbitProperties.getServerUrl())
                .path("/v1/candles/minutes/{unit}")
                .queryParam("market", marketType.getType())
                .queryParam("to", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime))
                .queryParam("count", count)
                .encode(StandardCharsets.UTF_8)
                .buildAndExpand(minuteType.getMinute())
                .toUri();
    }

    private String getJwtToken() {
        Algorithm algorithm = Algorithm.HMAC256(upbitProperties.getSecretKey());

        return JWT.create()
                .withClaim("access_key", upbitProperties.getAccessKey())
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);
    }

    private HttpHeaders getAuthorizationHeader(String jwtToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwtToken);
        httpHeaders.add("Accept", "application/json");
        return httpHeaders;
    }
}
