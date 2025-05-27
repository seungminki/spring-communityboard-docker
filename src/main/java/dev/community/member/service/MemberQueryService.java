package dev.community.member.service;

import dev.community.member.mapper.MemberMapper;
import dev.community.member.repository.MemberJpaRepository;
import dev.community.member.service.dto.MemberServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class MemberQueryService {

	private final MemberJpaRepository memberJpaRepository;
	private final MemberMapper memberMapper;

	public List<MemberServiceResponse> getMembers() {
		return memberMapper.toDtoList(memberJpaRepository.findAll());
	}
}
