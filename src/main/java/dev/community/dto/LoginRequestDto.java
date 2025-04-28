package dev.community.dto;

import dev.community.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

	@NotBlank(message = "회원 이메일 입력은 필수입니다.")
	private String email;

	@NotBlank(message = "회원 비밀번호 입력은 필수입니다.")
	private String password;

	@Builder
	public LoginRequestDto(Member member) {
		this.email = member.getEmail();
		this.password = member.getPassword();
	}

}
