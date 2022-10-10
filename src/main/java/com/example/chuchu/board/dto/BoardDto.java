package com.example.chuchu.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private long good;
    private boolean secret;

    @Builder
    public BoardDto(Long id, String title, String content, long good, boolean secret) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.good = good;
        this.secret = secret;
    }
}
