package com.example.chuchu.board.dto;

import com.example.chuchu.member.dto.MemberDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private Integer likeCount;
    private Integer viewCount;
    private Boolean secret;
    private MemberDTO writer;
    private List<String> tagList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public BoardDTO(Long id, String title, String content, Integer likeCount, Integer viewCount,
                    Boolean secret, MemberDTO writer, List<String> tagList,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.secret = secret;
        this.writer = writer;
        this.tagList = tagList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
