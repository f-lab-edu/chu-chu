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
public class CommentDTO {

    private Long id;
    private String content;
    private MemberDTO writer;
    private List<CommentDTO> children = new ArrayList<>();

    public CommentDTO(Long id, String content, MemberDTO writer) {
        this.id = id;
        this.content = content;
        this.writer = writer;
    }

    public static CommentDTO convertCommentToDto(Comment comment) {
        return comment.getIsDeleted() ?
                new CommentDTO(comment.getId(), "삭제된 댓글입니다.", null) :
                new CommentDTO(comment.getId(), comment.getContent(), new MemberDTO(comment.getWriter()));
    }
}
