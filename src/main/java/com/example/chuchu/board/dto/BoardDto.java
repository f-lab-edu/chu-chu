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
    private Integer likeCount;
    private Boolean secret;

    @Builder
    public BoardDto(Long id, String title, String content, Integer likeCount, Boolean secret) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.secret = secret;
    }
}
