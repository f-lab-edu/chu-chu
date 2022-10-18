package com.example.chuchu.board.service;

import com.example.chuchu.board.dto.BoardRequestDTO;
import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.entity.BoardType;
import com.example.chuchu.board.mapper.BoardMapper;
import com.example.chuchu.board.repository.BoardRepository;
import com.example.chuchu.comment.dto.CommentDTO;
import com.example.chuchu.comment.repository.CommentRepository;
import com.example.chuchu.common.errors.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lombok.Lombok.checkNotNull;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        checkNotNull(id, "boardId must be provided");
        return boardRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not found board id : " + id));
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board insert(BoardRequestDTO boardRequestDTO) {
        //TODO 저장할 때 필요한 정보 ()

//        Board board = BoardMapper.INSTANCE.toEntity(boardRequestDTO);
        return null;
    }

    @Transactional
    public Board update(BoardResponseDTO boardResponseDto, long id) {
        Board board = findById(id);
        return boardRepository.save(board.updateBoard(boardResponseDto));
    }

    @Transactional
    public Board delete(long id) {
        Board board = findById(id);
        boardRepository.delete(board);
        return board;
    }

    @Transactional(readOnly = true)
    public PageImpl<BoardResponseDTO> getBoardList(String query, BoardType boardType, Pageable pageable) {
        // query 에 들어올 수 있는 값 : 제목
        // pageable의 정렬 기준 : 최신순, 조회순, 좋아요 순
        if (query == null){
            query = "";
        }

        return boardRepository.getBoardList(query, boardType, pageable);
    }

    public BoardResponseDTO getBoardWithTag(Long id) {

        BoardResponseDTO boardResponseDTO = boardRepository.getBoardWithTag(id);
        List<CommentDTO> commentDTOList = commentRepository.findByBoardId(id);
        boardResponseDTO.setCommentDTOList(commentDTOList);
        return boardResponseDTO;
    }
}
