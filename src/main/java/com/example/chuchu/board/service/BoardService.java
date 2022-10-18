package com.example.chuchu.board.service;

import com.example.chuchu.board.dto.BoardRequestDTO;
import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.entity.BoardType;
import com.example.chuchu.board.repository.BoardRepository;
import com.example.chuchu.category.entity.Category;
import com.example.chuchu.category.repository.CategoryRepository;
import com.example.chuchu.comment.dto.CommentDTO;
import com.example.chuchu.comment.repository.CommentRepository;
import com.example.chuchu.common.errors.exception.NotFoundException;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        checkNotNull(id, "boardId must be provided");
        return boardRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not found board id : " + id));
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board insert(String boardType, BoardRequestDTO boardRequestDTO) {

        Member member = memberRepository.findById(boardRequestDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + boardRequestDTO.getMemberId()));

        Category category = categoryRepository.findById(boardRequestDTO.getCateId())
                .orElseThrow(() -> new NotFoundException("Could not found category id : " + boardRequestDTO.getCateId()));

        Board board = Board.builder()
                .title(boardRequestDTO.getTitle())
                .hashTag(boardRequestDTO.getHashTag())
                .content(boardRequestDTO.getContent())
                .isSecret(true)
                .boardType(BoardType.valueOf(boardType.toUpperCase()))
                .writer(member)
                .category(category)
                .build();

        return boardRepository.save(board);
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
