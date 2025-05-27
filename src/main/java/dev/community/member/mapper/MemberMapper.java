package dev.community.member.mapper;

import dev.community.member.entity.Member;
import dev.community.member.service.dto.MemberServiceResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
	MemberServiceResponse toDto(Member member);

	List<MemberServiceResponse> toDtoList(List<Member> members);
}
