package com.example.chuchu.comment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDTO {
    private Long memberId;
    private Long parentId;
    private String content;

    public CommentRequestDTO(String content) {
        this.content = content;
    }
}
