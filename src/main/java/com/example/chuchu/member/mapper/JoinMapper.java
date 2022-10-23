package com.example.chuchu.member.mapper;

import com.example.chuchu.common.global.GenericMapper;
import com.example.chuchu.member.dto.JoinDTO;
import com.example.chuchu.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JoinMapper extends GenericMapper<JoinDTO, Member> {
    JoinMapper INSTANCE = Mappers.getMapper(JoinMapper.class);
}
