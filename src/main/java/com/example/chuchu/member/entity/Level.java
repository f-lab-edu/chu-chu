package com.example.chuchu.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Level {

    BRONZE("BRONZE"),
    SILVER("SILVER"),
    PLATINUM("PLATINUM"),
    DIA("DIA"),
    MASTER("MASTER");

    private final String value;

}
