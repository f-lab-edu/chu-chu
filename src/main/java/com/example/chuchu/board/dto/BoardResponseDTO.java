package com.example.chuchu.board.dto;

import com.example.chuchu.board.entity.BoardType;
import com.example.chuchu.comment.dto.CommentResponseDTO;
import com.example.chuchu.member.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String hashTag;
    private String content;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private Boolean isSecret;
    private MemberDTO writer;
    private BoardType boardType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponseDTO> commentResponseDTOList;

    @Builder
    public BoardResponseDTO(Long id, String title, String hashTag, String content, Integer likeCount, Integer viewCount,
                            Integer commentCount, Boolean isSecret, BoardType boardType, MemberDTO writer,
                            List<CommentResponseDTO> commentResponseDTOList, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.hashTag = hashTag;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.boardType = boardType;
        this.isSecret = isSecret;
        this.writer = writer;
        this.commentResponseDTOList = commentResponseDTOList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Builder
    public BoardResponseDTO(Long id, String title, String hashTag, String content, Integer likeCount,
                            Integer viewCount, Integer commentCount, Boolean isSecret, MemberDTO writer, BoardType boardType) {
        this.id = id;
        this.title = title;
        this.hashTag = hashTag;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.isSecret = isSecret;
        this.writer = writer;
        this.boardType = boardType;
    }
}
