package com.example.chuchu.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDTO {
    private String boardType;
    private Long memberId;
    private Long cateId;
    private String title;
    private String hashTag;
    private String content;
    private Boolean isSecret;

    public BoardRequestDTO(String boardType, Long memberId, Long cateId, String title, String hashTag, String content, Boolean isSecret) {
        this.boardType = boardType;
        this.memberId = memberId;
        this.cateId = cateId;
        this.title = title;
        this.hashTag = hashTag;
        this.content = content;
        this.isSecret = isSecret;
    }
}
