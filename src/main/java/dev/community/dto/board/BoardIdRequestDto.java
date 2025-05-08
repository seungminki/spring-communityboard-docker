package dev.community.dto.board;

import jakarta.validation.constraints.NotNull;

public record BoardIdRequestDto (
	@NotNull(message = "글 번호는 필수입니다.") Long id
){ }
