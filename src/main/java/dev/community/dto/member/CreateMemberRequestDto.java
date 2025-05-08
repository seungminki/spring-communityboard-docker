package dev.community.dto.member;

import jakarta.validation.constraints.NotBlank;

public record CreateMemberRequestDto(
	@NotBlank(message = "닉네임은 필수입니다.") String name,
	@NotBlank(message = "이메일은 필수입니다.") String email,
	@NotBlank(message = "비밀번호는 필수입니다.") String password
) { }
