package com.example.chuchu.board.repository;

import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
