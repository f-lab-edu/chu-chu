package com.example.chuchu.board.repository;

import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.entity.BoardType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {

    // TODO 구체적인 타입은 추후에 추가
    PageImpl<BoardResponseDTO> getBoardList(String query, BoardType boardType, Pageable pageable);

    BoardResponseDTO getBoardWithTag(Long id);

    void addLikeCount(Board board);

    void subLikeCount(Board board);
}
