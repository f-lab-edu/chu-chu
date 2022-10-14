package com.example.chuchu.board.mapper;

import com.example.chuchu.board.dto.BoardDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.common.global.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper extends GenericMapper<BoardDTO, Board> {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);
}