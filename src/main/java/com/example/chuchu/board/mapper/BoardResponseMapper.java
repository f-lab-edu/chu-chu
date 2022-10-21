package com.example.chuchu.board.mapper;

import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.board.entity.Board;
import com.example.chuchu.common.global.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface  BoardResponseMapper extends GenericMapper<BoardResponseDTO, Board> {
    BoardResponseMapper INSTANCE = Mappers.getMapper(BoardResponseMapper.class);
}