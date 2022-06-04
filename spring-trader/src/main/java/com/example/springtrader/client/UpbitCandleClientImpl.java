package com.example.springtrader.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springtrader.config.properties.UpbitProperties;
import com.example.springtrader.enums.MarketType;
import com.trader.common.enums.MinuteType;
import com.trader.common.utils.MinuteCandle;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties
public class UpbitCandleClientImpl implements UpbitCandleClient {

    private final RestTemplate restTemplate;

    private final UpbitProperties upbitProperties;

    @Override
    public List<MinuteCandle> getMinuteCandles(MinuteType minuteType, MarketType marketType, int count, LocalDateTime localDateTime) {

        HttpHeaders authorizationHeader = getAuthorizationHeader(getJwtToken());
        HttpEntity<String> httpEntity = new HttpEntity<>(authorizationHeader);

        URI targetUrl = getMinuteCandlesUrl(minuteType, marketType, count, localDateTime);

        ResponseEntity<List<MinuteCandle>> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {});
        return result.getBody();
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
