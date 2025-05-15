package dev.community;

import jakarta.validation.constraints.NotNull;

public record BoardCreateRequestDto(
	@NotNull(message = "글 제목은 필수입니다.") String title,
	@NotNull(message = "글 내용은 필수입니다.") String content
){ }
