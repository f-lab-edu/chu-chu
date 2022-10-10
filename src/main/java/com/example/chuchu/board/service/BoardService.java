package com.example.chuchu.board.service;

import com.example.chuchu.board.dto.BoardDto;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.mapper.BoardMapper;
import com.example.chuchu.board.repository.BoardRepository;
import com.example.chuchu.common.errors.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lombok.Lombok.checkNotNull;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        checkNotNull(id, "boardId must be provided");
        return boardRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not found board id : " + id));
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board insert(BoardDto boardDto) {
        Board board = BoardMapper.INSTANCE.toEntity(boardDto);
        return boardRepository.save(board);
    }

    @Transactional
    public Board update(BoardDto boardDto, long id) {
        Board board = findById(id);
        return boardRepository.save(board.updateBoard(boardDto));
    }

    @Transactional
    public Board delete(long id) {
        Board board = findById(id);
        boardRepository.delete(board);
        return board;
    }
}
