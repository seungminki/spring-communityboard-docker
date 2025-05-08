package dev.community.dto.member;

import dev.community.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

	private Long id;
	private String name;
	private String email;

	@Builder
	public MemberResponseDto(Member member) {
		this.id = member.getId();
		this.name = member.getName();
		this.email = member.getEmail();
	}

}

