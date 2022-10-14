package com.example.chuchu.board.repository;

import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.entity.BoardTagMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTagMapRepository extends JpaRepository<BoardTagMap, Long> {
}
