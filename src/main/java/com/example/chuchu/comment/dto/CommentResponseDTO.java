package com.example.chuchu.comment.dto;

import com.example.chuchu.comment.entity.Comment;
import com.example.chuchu.member.dto.MemberDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDTO {

    private Long id;
    private String content;
    private MemberDTO writer;
    private List<CommentResponseDTO> children = new ArrayList<>();

    public CommentResponseDTO(Long id, String content, MemberDTO writer) {
        this.id = id;
        this.content = content;
        this.writer = writer;
    }

    public static CommentResponseDTO convertCommentToDto(Comment comment) {
        return comment.getIsDeleted() ?
                new CommentResponseDTO(comment.getId(), "삭제된 댓글입니다.", null) :
                new CommentResponseDTO(comment.getId(), comment.getContent(), new MemberDTO(comment.getWriter()));
    }
}
