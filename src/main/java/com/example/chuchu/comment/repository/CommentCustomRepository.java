package com.example.chuchu.comment.repository;

import com.example.chuchu.comment.dto.CommentResponseDTO;

import java.util.List;

public interface CommentCustomRepository {

    List<CommentResponseDTO> findByBoardId(Long id);
}
