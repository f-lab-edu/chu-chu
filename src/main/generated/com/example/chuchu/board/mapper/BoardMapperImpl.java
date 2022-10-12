package com.example.chuchu.board.mapper;

import com.example.chuchu.board.dto.BoardDto;
import com.example.chuchu.board.entity.Board;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-12T22:38:25+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public BoardDto toDto(Board e) {
        if ( e == null ) {
            return null;
        }

        BoardDto.BoardDtoBuilder boardDto = BoardDto.builder();

        boardDto.id( e.getId() );
        boardDto.title( e.getTitle() );
        boardDto.content( e.getContent() );
        boardDto.likeCount( e.getLikeCount() );
        boardDto.secret( e.getSecret() );

        return boardDto.build();
    }

    @Override
    public Board toEntity(BoardDto d) {
        if ( d == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        board.title( d.getTitle() );
        board.content( d.getContent() );
        board.likeCount( d.getLikeCount() );
        if ( d.getSecret() != null ) {
            board.secret( d.getSecret() );
        }

        return board.build();
    }

    @Override
    public List<BoardDto> toDtoList(List<Board> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BoardDto> list = new ArrayList<BoardDto>( entityList.size() );
        for ( Board board : entityList ) {
            list.add( toDto( board ) );
        }

        return list;
    }

    @Override
    public List<Board> toEntityList(List<BoardDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Board> list = new ArrayList<Board>( dtoList.size() );
        for ( BoardDto boardDto : dtoList ) {
            list.add( toEntity( boardDto ) );
        }

        return list;
    }
}
