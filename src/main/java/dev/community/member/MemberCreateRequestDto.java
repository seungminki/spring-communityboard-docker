package dev.community.member;

import jakarta.validation.constraints.NotNull;

public record MemberCreateRequestDto(
	@NotNull(message = "회원 이메일은 필수입니다.") String email,
	@NotNull(message = "회원 이름은 필수입니다.") String name,
	@NotNull(message = "회원 비밀번호는 필수입니다.") String password
){ }
