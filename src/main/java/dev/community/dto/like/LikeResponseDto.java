package dev.community.dto.like;

import dev.community.dto.board.BoardResponseDto;
import dev.community.dto.member.MemberResponseDto;
import dev.community.entity.Like;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeResponseDto {

	private Long id;
	private MemberResponseDto member;
	private BoardResponseDto board;

	@Builder
	public LikeResponseDto(Like like) {
		this.id = like.getId();
		this.member = new MemberResponseDto(like.getMember());
		this.board = new BoardResponseDto(like.getBoard());
	}

}
