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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRequestMapper commentRequestMapper;

    @Transactional
    public Comment insert(Long boardId, CommentRequestDTO commentRequestDTO) {

        Member member = memberRepository.findById(commentRequestDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + commentRequestDTO.getMemberId()));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + boardId));

        Comment comment = commentRequestMapper.toEntity(commentRequestDTO);

        Comment parentComment;
        if (commentRequestDTO.getParentId() != null) {
            parentComment = commentRepository.findById(commentRequestDTO.getParentId())
                    .orElseThrow(() -> new NotFoundException("Could not found comment id : " + commentRequestDTO.getParentId()));
            comment.updateParent(parentComment);
        }

        comment.updateWriter(member);
        comment.updateBoard(board);

        return commentRepository.save(comment);

    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findCommentByIdWithParent(commentId)
                .orElseThrow(() -> new NotFoundException("Could not found comment id : " + commentId));
        if(comment.getChildren().size() != 0) { // 자식이 있으면 상태만 변경
            comment.changeIsDeleted(true);
        } else { // 삭제 가능한 조상 댓글을 구해서 삭제
            commentRepository.delete(getDeletableAncestorComment(comment));
        }
    }

    private Comment getDeletableAncestorComment(Comment comment) {
        Comment parent = comment.getParent(); // 현재 댓글의 부모를 구함
        if(parent != null && parent.getChildren().size() == 1 && parent.getIsDeleted())
            // 부모가 있고, 부모의 자식이 1개(지금 삭제하는 댓글)이고, 부모의 삭제 상태가 TRUE인 댓글이라면 재귀
            return getDeletableAncestorComment(parent);
        return comment; // 삭제해야하는 댓글 반환
    }

    @Transactional
    public void update(Long commentId, CommentRequestDTO commentRequestDTO) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Could not found comment id : " + commentId));
        //TODO 해당 메서드를 호출하는 사옹자와 댓글을 작성한 작성자가 같은지 확인하는 로직이 필요함
        comment.updateContent(commentRequestDTO.getContent());
    }
}
