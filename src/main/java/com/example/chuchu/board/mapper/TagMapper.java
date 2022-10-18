package com.example.chuchu.board.mapper;

import com.example.chuchu.board.dto.TagDTO;
import com.example.chuchu.board.entity.Tag;
import com.example.chuchu.common.global.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper extends GenericMapper<TagDTO, Tag> {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
}
