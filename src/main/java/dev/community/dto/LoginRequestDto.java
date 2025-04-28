package dev.community.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto (
	@NotBlank(message = "회원 이메일 입력은 필수입니다.") String email,
	@NotBlank(message = "회원 비밀번호 입력은 필수입니다.") String password
){ }
