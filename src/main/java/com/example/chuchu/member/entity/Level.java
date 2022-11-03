package com.example.chuchu.member.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum Level {

    BRONZE("BRONZE"),
    SILVER("SILVER"),
    PLATINUM("PLATINUM"),
    DIA("DIA"),
    MASTER("MASTER");

    private final String value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Level fromLevel(String value) {
        return Stream.of(Level.values())
                .filter(level -> StringUtils.equals(level.getValue(), value))
                .findFirst()
                .orElse(null);
    }

}
