package dev.community.member.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
	@NotBlank(message = "회원 이메일은 필수입니다.") String email,
	@NotBlank(message = "회원 비밀번호는 필수입니다.") String password
) {
}
