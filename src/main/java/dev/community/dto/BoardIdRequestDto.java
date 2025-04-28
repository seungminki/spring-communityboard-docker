package dev.community.dto;

import jakarta.validation.constraints.NotBlank;

public record BoardIdRequestDto (
	@NotBlank(message = "글 번호는 필수입니다.") Long id
){ }
