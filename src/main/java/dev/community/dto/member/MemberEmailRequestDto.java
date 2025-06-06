package dev.community.dto.member;

import jakarta.validation.constraints.NotBlank;

public record MemberEmailRequestDto(
	@NotBlank(message = "이메일은 필수입니다.") String email
) { }

