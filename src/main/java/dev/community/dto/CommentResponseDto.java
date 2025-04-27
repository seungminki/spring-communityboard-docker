package dev.community.dto;

import dev.community.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {

	private Long id;
	private String content;
	private MemberResponseDto member;
	private BoardResponseDto board;

	@Builder
	public CommentResponseDto(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.member = new MemberResponseDto(comment.getMember());
		this.board = new BoardResponseDto(comment.getBoard());
	}

}
