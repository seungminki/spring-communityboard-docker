package dev.community.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBoardRequestDto(
	@NotBlank(message = "글 제목은 필수입니다.") String title,
	@NotBlank(message = "글 내용은 필수입니다.") String content
){ }
