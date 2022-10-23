package com.example.chuchu.heart.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRequestDTO {

    private Long memberId;
    private Long boardId;

    public HeartRequestDTO(Long memberId, Long boardId) {
        this.memberId = memberId;
        this.boardId = boardId;
    }
}
