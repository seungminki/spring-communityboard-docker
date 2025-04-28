package dev.community.dto;

import dev.community.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

	@NotBlank(message = "댓글 번호는 필수입니다.")
	private Long id;

	@NotBlank(message = "댓글 내용은 필수입니다.")
	private String content;

	@Builder
	public CommentRequestDto(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
	}

}
