package com.example.chuchu.board.repository;

import com.example.chuchu.board.dto.BoardDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.entity.BoardType;
import com.example.chuchu.board.mapper.BoardMapper;
import com.example.chuchu.common.errors.exception.NotFoundException;
import com.example.chuchu.member.dto.MemberDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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

        List<BoardDTO> boardDTOList = boardList.stream().map(e -> new BoardDTO(e.getId(), e.getTitle(), e.getContent(), e.getLikeCount(), e.getViewCount(),
                e.getCommentCount(), e.getIsSecret(), e.getBoardType(), (e.getWriter() != null) ? new MemberDTO(e.getWriter()) : null,
                (e.getBoardTagMapList() != null) ? e.getBoardTagMapList().stream().map(l -> l.getTag().getName()).collect(Collectors.toList()) : null,
                null, e.getCreatedAt(), e.getUpdatedAt())).collect(Collectors.toList());

        return new PageImpl<>(boardDTOList, pageable, count);
    }

    @Override
    public BoardDTO getBoardWithTag(Long id) {

        // TODO 중복 조회를 어떻게 예방할 것인지 고민해야함
        queryFactory.update(board)
                .set(board.viewCount, board.viewCount.add(1))
                .where(board.id.eq(id))
                .execute();

        Board board1 = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category).fetchJoin()
                .leftJoin(board.writer, member).fetchJoin()
                .leftJoin(board.boardTagMapList, boardTagMap).fetchJoin()
                .leftJoin(boardTagMap.tag, tag).fetchJoin()
                .where(board.id.eq(id))
                .fetchOne();

        if (board1 == null) {
            throw new NotFoundException("Could not found board id : " + id);
        }

        return new BoardDTO(board1.getId(), board1.getTitle(), board1.getContent(), board1.getLikeCount(), board1.getViewCount(),
                board1.getCommentCount(), board1.getIsSecret(), board1.getBoardType(), (board1.getWriter() != null) ?new MemberDTO(board1.getWriter()) : null,
                (board1.getBoardTagMapList() != null) ? board1.getBoardTagMapList().stream().map(e -> e.getTag().getName()).collect(Collectors.toList()) : null,
                null, board1.getCreatedAt(), board1.getUpdatedAt());

    }
}
