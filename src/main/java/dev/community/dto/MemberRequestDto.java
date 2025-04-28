package dev.community.dto;

import dev.community.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequestDto {

	@NotBlank(message = "닉네임은 필수입니다.")
	private String username;

	@NotBlank(message = "이메일은 필수입니다.")
	private String email;

	@NotBlank(message = "비밀번호는 필수입니다.")
	private String password;

	@Builder
	public MemberRequestDto(Member member) {
		this.username = member.getName();
		this.email = member.getEmail();
		this.password = member.getPassword();
	}

}
