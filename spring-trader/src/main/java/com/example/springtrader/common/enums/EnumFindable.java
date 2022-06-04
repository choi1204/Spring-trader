package com.example.springtrader.common.enums;

import java.util.Arrays;

public interface EnumFindable {
    String getType();

    static<T extends EnumFindable> T find(String type, T[] values) {
        return Arrays.stream(values)
                .filter(value -> value.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("정의되지 않은 타입입니다."));
    }
}
