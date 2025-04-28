package dev.community.dto;

import dev.community.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

	@NotBlank(message = "글 제목은 필수입니다.")
	private String title;

	@NotBlank(message = "글 내용은 필수입니다.")
	private String content;

	@Builder
	public BoardRequestDto(Board board) {
		this.title = board.getTitle();
		this.content = board.getContent();
	}

}
