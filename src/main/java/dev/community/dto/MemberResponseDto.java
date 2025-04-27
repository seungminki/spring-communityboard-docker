package dev.community.dto;

import dev.community.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

	private Long id;
	private String username;
	private String email;

	@Builder
	public MemberResponseDto(Member member) {
		this.id = member.getId();
		this.username = member.getName();
		this.email = member.getEmail();
	}

}

