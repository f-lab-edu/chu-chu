package com.example.chuchu.comment.repository;

import com.example.chuchu.board.repository.BoardCustomRepository;
import com.example.chuchu.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
}
