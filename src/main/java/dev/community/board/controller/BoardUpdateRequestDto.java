package dev.community.board.controller;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BoardUpdateRequestDto(
	@NotNull(message = "글 제목은 필수입니다.") String title,
	@NotNull(message = "글 내용은 필수입니다.") String content
){ }
