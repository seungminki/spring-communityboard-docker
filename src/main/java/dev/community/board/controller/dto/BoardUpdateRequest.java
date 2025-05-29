package dev.community.board.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record BoardUpdateRequest(
	@NotBlank(message = "글 제목은 필수입니다.") String title,
	@NotBlank(message = "글 내용은 필수입니다.") String content
) {
}
