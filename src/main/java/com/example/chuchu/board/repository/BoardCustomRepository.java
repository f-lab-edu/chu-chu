package com.example.chuchu.board.repository;

import com.example.chuchu.board.dto.BoardDTO;
import com.example.chuchu.board.entity.BoardType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {

    // TODO 구체적인 타입은 추후에 추가
    PageImpl<BoardDTO> getBoardList(String query, BoardType boardType, Pageable pageable);

    BoardDTO getBoardWithTag(Long id);
}
