package com.example.chuchu.comment.service;

import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.repository.BoardRepository;
import com.example.chuchu.comment.dto.CommentRequestDTO;
import com.example.chuchu.comment.entity.Comment;
import com.example.chuchu.comment.mapper.CommentRequestMapper;
import com.example.chuchu.comment.repository.CommentRepository;
import com.example.chuchu.common.errors.exception.NotFoundException;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRequestMapper commentRequestMapper;

    @Transactional
    public void insert(Long boardId, CommentRequestDTO commentRequestDTO) {

        Member member = memberRepository.findById(commentRequestDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + commentRequestDTO.getMemberId()));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + boardId));

        Comment comment = commentRequestMapper.toEntity(commentRequestDTO);

        Comment parentComment = null;
        if (commentRequestDTO.getParentId() != null) {
            parentComment = commentRepository.findById(commentRequestDTO.getParentId())
                    .orElseThrow(() -> new NotFoundException("Could not found comment id : " + commentRequestDTO.getParentId()));
            comment.updateParent(parentComment);
        }

        comment.updateWriter(member);
        comment.updateBoard(board);

        commentRepository.save(comment);

    }
}
