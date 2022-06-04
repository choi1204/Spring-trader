package com.example.springtrader.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ModeType implements EnumFindable {
    TEST("test"),
    REAL("real"),
    CRAWL("crawl");

    private final String type;

    public static ModeType find(String type) {
        return EnumFindable.find(type, ModeType.values());
    }

}
