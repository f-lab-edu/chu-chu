package com.example.chuchu.board.repository;

import com.example.chuchu.board.dto.BoardResponseDTO;
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
import static com.example.chuchu.board.entity.QCategory.category;
import static com.example.chuchu.member.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public PageImpl<BoardResponseDTO> getBoardList(String query, BoardType boardType, Pageable pageable) {

        List<Board> boardList = queryFactory
                .selectFrom(board)
                .leftJoin(board.category, category).fetchJoin()
                .leftJoin(board.writer, member).fetchJoin()
                .where(board.title.contains(query),
                        board.boardType.eq(boardType))
                .fetch();

        Long count = queryFactory
                .select(board.count())
                .from(board)
                .where(board.title.contains(query),
                       board.boardType.eq(boardType))
                .fetchOne();

        List<BoardResponseDTO> boardResponseDTOList = BoardMapper.INSTANCE.toDtoList(boardList);

        return new PageImpl<>(boardResponseDTOList, pageable, count);
    }

    @Override
    public BoardResponseDTO getBoardWithTag(Long id) {

        // TODO 중복 조회를 어떻게 예방할 것인지 고민해야함
        queryFactory.update(board)
                .set(board.viewCount, board.viewCount.add(1))
                .where(board.id.eq(id))
                .execute();

        Board board1 = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category).fetchJoin()
                .leftJoin(board.writer, member).fetchJoin()
                .where(board.id.eq(id))
                .fetchOne();

        if (board1 == null) {
            throw new NotFoundException("Could not found board id : " + id);
        }

        return BoardMapper.INSTANCE.toDto(board1);

    }
}
