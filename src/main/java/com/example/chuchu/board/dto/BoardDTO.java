package com.example.chuchu.board.dto;

import com.example.chuchu.board.entity.BoardType;
import com.example.chuchu.comment.dto.CommentDTO;
import com.example.chuchu.member.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private Boolean isSecret;
    private MemberDTO writer;
    private BoardType boardType;
    private List<String> tagList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentDTO> commentDTOList;

    @Builder
    public BoardDTO(Long id, String title, String content, Integer likeCount, Integer viewCount,
                    Integer commentCount, Boolean isSecret, BoardType boardType, MemberDTO writer, List<String> tagList,
                    List<CommentDTO> commentDTOList, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.boardType = boardType;
        this.isSecret = isSecret;
        this.writer = writer;
        this.tagList = tagList;
        this.commentDTOList = commentDTOList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
