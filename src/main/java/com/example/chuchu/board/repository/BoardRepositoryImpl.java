package com.example.chuchu.board.repository;

import com.example.chuchu.board.dto.BoardDTO;
import com.example.chuchu.board.dto.TagDTO;
import com.example.chuchu.board.entity.*;
import com.example.chuchu.board.mapper.BoardMapper;
import com.example.chuchu.board.mapper.TagMapper;
import com.example.chuchu.member.dto.MemberDTO;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.chuchu.board.entity.QBoard.board;
import static com.example.chuchu.board.entity.QBoardTagMap.boardTagMap;
import static com.example.chuchu.board.entity.QCategory.category;
import static com.example.chuchu.board.entity.QTag.tag;
import static com.example.chuchu.member.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardCustomRepository{

    private final JPAQueryFactory queryFactory;
    private final TagMapper tagMapper;
    @Override
    public PageImpl<BoardDTO> getBoardList(String query, Pageable pageable) {

        List<Long> ids = queryFactory.
                select(board.id)
                .from(board)
                .where(board.title.contains(query))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(board.count())
                .from(board)
                .where(board.title.contains(query))
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
                e.getSecret(), new MemberDTO(e.getWriter()),
                (e.getBoardTagMapList() != null) ? e.getBoardTagMapList().stream().map(l -> l.getTag().getName()).collect(Collectors.toList()) : null,
                e.getCreatedAt(), e.getUpdatedAt())).collect(Collectors.toList());

        return new PageImpl<>(boardDTOList, pageable, count);
    }
}
