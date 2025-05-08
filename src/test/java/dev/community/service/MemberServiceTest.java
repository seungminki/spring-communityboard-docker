package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.dto.LoginResponseDto;
import dev.community.dto.CreateMemberRequestDto;
import dev.community.dto.MemberResponseDto;
import dev.community.entity.Member;
import dev.community.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest {

	private MemberService memberService;
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		memberRepository = mock(MemberRepository.class);
		memberService = new MemberService(memberRepository);
	}

	@Test
	void 회원가입_성공() {
		// given
		CreateMemberRequestDto requestDto = new CreateMemberRequestDto("name", "email@example.com", "password");

		// when
		memberService.join(requestDto);

		// then
		verify(memberRepository).save(any(Member.class));
	}

	@Test
	void 로그인_성공() {
		// given
		Member member = Member.builder()
			.name("name")
			.email("email@example.com")
			.password("password")
			.build();

		when(memberRepository.findByEmail("email@example.com")).thenReturn(Optional.of(member));

		// when
		LoginResponseDto loginDto = memberService.login("email@example.com", "password");

		// then
		assertThat(loginDto.email()).isEqualTo("email@example.com");
	}

	@Test
	void 로그인_실패_비밀번호_불일치() {
		// given
		Member member = Member.builder()
			.name("name")
			.email("email@example.com")
			.password("password")
			.build();

		when(memberRepository.findByEmail("email@example.com")).thenReturn(Optional.of(member));

		// then
		assertThatThrownBy(() -> memberService.login("email@example.com", "wrongPassword"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ErrorMessage.NOT_MATCHES_ID_AND_PW.getMessage());
	}

	@Test
	void 이름수정_성공() {
		// given
		Member member = Member.builder()
			.name("oldName")
			.email("email@example.com")
			.password("password")
			.build();

		when(memberRepository.findByEmail("email@example.com")).thenReturn(Optional.of(member));

		// when
		MemberResponseDto updated = memberService.updateName("email@example.com", "newName");

		// then
		assertThat(updated.getName()).isEqualTo("newName");
		verify(memberRepository).save(any(Member.class));
	}

	@Test
	void 회원삭제_성공() {
		// given
		Member member = Member.builder()
			.name("name")
			.email("email@example.com")
			.password("password")
			.build();

		when(memberRepository.findByEmail("email@example.com")).thenReturn(Optional.of(member));

		// when
		memberService.deleteMember("email@example.com");

		// then
		verify(memberRepository).delete(member);
	}

	@Test
	void 회원가입_중복닉네임_검증() {
		// given
		when(memberRepository.findByName("name")).thenReturn(Optional.of(mock(Member.class)));

		// then
		assertThatThrownBy(() -> memberService.join(new CreateMemberRequestDto("name", "email@example.com", "password")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ErrorMessage.ALREADY_EXITS_MEMBER.getMessage());
	}
}

