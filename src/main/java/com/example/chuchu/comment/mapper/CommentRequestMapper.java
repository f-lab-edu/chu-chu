package com.example.chuchu.comment.mapper;

import com.example.chuchu.comment.dto.CommentRequestDTO;
import com.example.chuchu.comment.entity.Comment;
import com.example.chuchu.common.global.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentRequestMapper extends GenericMapper<CommentRequestDTO, Comment> {
    CommentRequestMapper INSTANCE = Mappers.getMapper(CommentRequestMapper.class);
}
