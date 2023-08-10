package com.wanted.member.mapper;

import com.wanted.member.dto.MemberDto;
import com.wanted.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberDtoToMember(MemberDto memberDto);
    MemberDto memberToMemberDto(Member member);

}
