package com.example.chuchu.board.service;

import com.example.chuchu.board.dto.BoardRequestDTO;
import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.entity.BoardType;
import com.example.chuchu.board.mapper.BoardRequestMapper;
import com.example.chuchu.board.repository.BoardRepository;
import com.example.chuchu.category.entity.Category;
import com.example.chuchu.category.repository.CategoryRepository;
import com.example.chuchu.comment.dto.CommentResponseDTO;
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
    private final BoardRequestMapper boardRequestMapper;

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

        Member member = memberRepository.findById(boardRequestDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + boardRequestDTO.getMemberId()));

        Category category = categoryRepository.findById(boardRequestDTO.getCateId())
                .orElseThrow(() -> new NotFoundException("Could not found category id : " + boardRequestDTO.getCateId()));

        Board board = boardRequestMapper.toEntity(boardRequestDTO);
        board.updateMember(member);
        board.updateCategory(category);

        return boardRepository.save(board);
    }

    @Transactional
    public Board update(BoardRequestDTO boardRequestDTO, Long id) {

        Board board = findById(id);
        Category category = categoryRepository.findById(boardRequestDTO.getCateId())
                .orElseThrow(() -> new NotFoundException("Could not found category id : " + boardRequestDTO.getCateId()));

        return board.updateBoard(boardRequestDTO, category);
    }

    @Transactional
    public Board delete(long id) {
        Board board = findById(id);
        boardRepository.delete(board);
        return board;
    }

    @Transactional(readOnly = true)
    public PageImpl<BoardResponseDTO> getBoardList(String query, BoardType boardType, Pageable pageable) {

        if (query == null){
            query = "";
        }

        return boardRepository.getBoardList(query, boardType, pageable);
    }

    @Transactional
    public BoardResponseDTO getBoardWithTag(Long id) {

        BoardResponseDTO boardResponseDTO = boardRepository.getBoardWithTag(id);
        List<CommentResponseDTO> commentResponseDTOList = commentRepository.findByBoardId(id);
        boardResponseDTO.setCommentResponseDTOList(commentResponseDTOList);
        return boardResponseDTO;
    }

    @Transactional
    public void viewCountUp(Long boardId) {
        Board board = findById(boardId);
        board.viewCountUp(board);
    }
}
