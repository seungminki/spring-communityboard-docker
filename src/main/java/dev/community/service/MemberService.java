package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.dto.LoginResponseDto;
import dev.community.dto.CreateMemberRequestDto;
import dev.community.entity.Member;
import dev.community.dto.MemberResponseDto;
import dev.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberResponseDto join(CreateMemberRequestDto memberRequestDto) {
		validateDuplicateMember(memberRequestDto);
		Member newmember = Member.builder()
			.name(memberRequestDto.name())
			.email(memberRequestDto.email())
			.password(memberRequestDto.password())
			.build();

		memberRepository.save(newmember);

		return new MemberResponseDto(newmember);
	}

	public LoginResponseDto login(String memberEmail, String password) {
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));

		if (member.getPassword().equals(password)) {
			return new LoginResponseDto(member.getEmail());
		} else {
			throw new IllegalArgumentException(ErrorMessage.NOT_MATCHES_ID_AND_PW.getMessage());
		}
	}

	public MemberResponseDto updateName(String memberEmail, String newName) {
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_TOKEN.getMessage()));

		Member newMember = Member.builder()
			.name(newName)
			.email(member.getEmail())
			.password(member.getPassword())
			.build();

		memberRepository.save(newMember);

		return new MemberResponseDto(newMember);
	}

	public void deleteMember(String memberEmail) {
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_TOKEN.getMessage()));

		memberRepository.delete(member);
	}

	private void validateDuplicateMember(CreateMemberRequestDto memberRequestDto) {
		memberRepository.findByName(memberRequestDto.name())
			.ifPresent(m -> {
				throw new IllegalArgumentException(ErrorMessage.ALREADY_EXITS_MEMBER.getMessage());
			});

		memberRepository.findByEmail(memberRequestDto.name())
			.ifPresent(m -> {
				throw new IllegalArgumentException(ErrorMessage.ALREADY_EXITS_MEMBER.getMessage());
			});
	}

}
