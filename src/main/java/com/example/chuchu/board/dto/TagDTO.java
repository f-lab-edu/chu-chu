package com.example.chuchu.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TagDTO {
    private String name;

    @Builder
    @QueryProjection
    public TagDTO(String name) {
        this.name = name;
    }
}
