package com.example.chuchu.member.mapper;

import com.example.chuchu.common.global.GenericMapper;
import com.example.chuchu.member.dto.MemberDTO;
import com.example.chuchu.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<MemberDTO, Member> {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);
}
