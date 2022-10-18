package com.example.chuchu.board.mapper;

import com.example.chuchu.board.dto.BoardRequestDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.common.global.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardRequestMapper extends GenericMapper<BoardRequestDTO, Board> {
    BoardRequestMapper INSTANCE = Mappers.getMapper(BoardRequestMapper.class);
}
