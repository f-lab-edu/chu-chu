package com.example.chuchu.comment.repository;

import com.example.chuchu.comment.dto.CommentResponseDTO;
import com.example.chuchu.comment.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentCustomRepository {

    List<CommentResponseDTO> findByBoardId(Long id);

    Optional<Comment> findCommentByIdWithParent(Long id);
}
