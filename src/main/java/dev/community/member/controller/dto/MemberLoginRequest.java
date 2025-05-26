package dev.community.member.controller.dto;

import jakarta.validation.constraints.NotNull;

public record MemberLoginRequest(
	@NotNull(message = "회원 이메일은 필수입니다.") String email,
	@NotNull(message = "회원 비밀번호는 필수입니다.") String password
){ }
