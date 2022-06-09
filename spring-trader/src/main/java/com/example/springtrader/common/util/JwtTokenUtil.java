package com.example.springtrader.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springtrader.common.properties.UpbitProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.util.UUID;

@RequiredArgsConstructor
public class JwtTokenUtil {

    public static String getJwtToken(String secretKey, String accessKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);
    }

    public static HttpHeaders getAuthorizationHeader(String jwtToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwtToken);
        httpHeaders.add("Content-Type", "application/json");
        return httpHeaders;
    }

    public static HttpHeaders getAuthorizationHeader(String secretKey, String accessKey) {
        String jwtToken = getJwtToken(secretKey, accessKey);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwtToken);
        httpHeaders.add("Content-Type", "application/json");
        return httpHeaders;
    }
}
