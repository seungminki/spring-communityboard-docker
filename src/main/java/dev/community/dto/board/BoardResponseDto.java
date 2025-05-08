package dev.community.dto.board;

import dev.community.dto.member.MemberResponseDto;
import dev.community.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {

	private Long id;
	private String title;
	private String content;
	private MemberResponseDto member;
	private int viewCount;

	@Builder
	public BoardResponseDto(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.member = new MemberResponseDto(board.getMember());
		this.viewCount = board.getViewCount();
	}

}
