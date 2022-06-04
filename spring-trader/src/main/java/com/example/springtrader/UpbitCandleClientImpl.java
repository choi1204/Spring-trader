package com.example.springtrader;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.trader.common.enums.MarketFlowType;
import com.trader.common.enums.MarketUnit;
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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.error.Mark;

import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties
public class UpbitCandleClientImpl implements UpbitCandleClient {

    private final RestTemplate restTemplate;

    private final UpbitProperties upbitProperties;

    @Override
    public List<MinuteCandle> getMinuteCandles(MinuteType minuteType, MarketUnit marketUnit, int count, LocalDateTime localDateTime) {

        String jwtToken = getJwtToken();
        HttpHeaders authorizationHeader = getAuthorizationHeader(jwtToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(authorizationHeader);

        URI targetUrl = UriComponentsBuilder
                .fromUriString(upbitProperties.getServerUrl())
                .path("/v1/candles/minutes/{unit}")
                .queryParam("market", marketUnit.getType() + "-" + MarketUnit.BTC.getType())
                .queryParam("to", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime))
                .queryParam("count", count)
                .encode(StandardCharsets.UTF_8)
                .buildAndExpand(minuteType.getMinute())
                .toUri();

        ResponseEntity<List<MinuteCandle>> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {});
        return result.getBody();
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
