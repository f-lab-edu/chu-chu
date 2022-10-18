package com.example.chuchu.comment.repository;

import com.example.chuchu.comment.dto.CommentDTO;

import java.util.List;

public interface CommentCustomRepository {

    List<CommentDTO> findByBoardId(Long id);
}
