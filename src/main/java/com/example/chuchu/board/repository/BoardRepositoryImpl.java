package com.example.chuchu.board.repository;

import com.example.chuchu.board.dto.BoardDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.entity.BoardType;
import com.example.chuchu.board.mapper.BoardMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.chuchu.board.entity.QBoard.board;
import static com.example.chuchu.board.entity.QBoardTagMap.boardTagMap;
import static com.example.chuchu.board.entity.QCategory.category;
import static com.example.chuchu.board.entity.QTag.tag;
import static com.example.chuchu.member.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public PageImpl<BoardDTO> getBoardList(String query, BoardType boardType, Pageable pageable) {

        List<Long> ids = queryFactory.
                select(board.id)
                .from(board)
                .where(board.title.contains(query),
                       board.boardType.eq(boardType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(board.count())
                .from(board)
                .where(board.title.contains(query),
                       board.boardType.eq(boardType))
                .fetchOne();

        List<Board> boardList = queryFactory
                .selectFrom(board).distinct()
                .leftJoin(board.category, category).fetchJoin()
                .leftJoin(board.writer, member).fetchJoin()
                .leftJoin(board.boardTagMapList, boardTagMap).fetchJoin()
                .leftJoin(boardTagMap.tag, tag).fetchJoin()
                .where(board.id.in(ids))
                .fetch();

        List<BoardDTO> boardDTOList = BoardMapper.INSTANCE.toDtoList(boardList);

        return new PageImpl<>(boardDTOList, pageable, count);
    }
}
