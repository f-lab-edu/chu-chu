package com.example.chuchu.board.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BoardType {

    COMPLAIN("complain"),
    IDEA("idea");

    private final String value;
}
