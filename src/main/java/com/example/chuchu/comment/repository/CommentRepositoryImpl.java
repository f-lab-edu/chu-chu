package com.example.chuchu.comment.repository;

import com.example.chuchu.comment.dto.CommentDTO;
import com.example.chuchu.comment.entity.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.chuchu.board.entity.QComment.comment;
import static com.example.chuchu.comment.dto.CommentDTO.convertCommentToDto;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentDTO> findByBoardId(Long id) {

        List<Comment> comments = queryFactory.selectFrom(comment)
                .leftJoin(comment.parent).fetchJoin()
                .where(comment.board.id.eq(id))
                .orderBy(comment.parent.id.asc().nullsFirst(),
                        comment.createdAt.asc())
                .fetch();

        List<CommentDTO> commentDTOList = new ArrayList<>();
        Map<Long, CommentDTO> commentDTOHashMap = new HashMap<>();

        comments.forEach(c -> {
            CommentDTO commentDTO = convertCommentToDto(c);
            commentDTOHashMap.put(commentDTO.getId(), commentDTO);
            if (c.getParent() != null) commentDTOHashMap.get(c.getParent().getId()).getChildren().add(commentDTO);
            else commentDTOList.add(commentDTO);
        });
        return commentDTOList;
    }
}
