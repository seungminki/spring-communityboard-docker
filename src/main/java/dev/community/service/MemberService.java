package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.entity.Member;
import dev.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public Member join(Member member) {
		validateDuplicateMember(member);
		Member newMember = Member.builder()
			.name(member.getName())
			.email(member.getEmail())
			.password(member.getPassword())
			.build();

		return memberRepository.save(newMember);
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalArgumentException(ErrorMessage.ALREADY_EXITS_MEMBER.getMessage());
			});

		memberRepository.findByEmail(member.getEmail())
			.ifPresent(m -> {
				throw new IllegalArgumentException(ErrorMessage.ALREADY_EXITS_MEMBER.getMessage());
			});
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Member login(String memberEmail, String password) {
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));

		if (member.getPassword().equals(password)) {
			return member;
		} else {
			throw new IllegalArgumentException(ErrorMessage.NOT_MATCHES_ID_AND_PW.getMessage());
		}
	}

	public Member updateName(Principal principal, String newName) {

		Member member = memberRepository.findByEmail(principal.getName())
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_TOKEN.getMessage()));

		member.setName(newName);

		return memberRepository.save(member);
	}

	public void deleteMember(Principal principal) {
		Member member = memberRepository.findByEmail(principal.getName())
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_TOKEN.getMessage()));

		memberRepository.delete(member);
	}

}
