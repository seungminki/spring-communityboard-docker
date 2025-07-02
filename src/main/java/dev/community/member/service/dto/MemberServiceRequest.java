package dev.community.member.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MemberServiceRequest(
	@NotBlank(message = "회원 이메일은 필수입니다.") String email,
	@NotBlank(message = "회원 이름은 필수입니다.") String name,
	@NotBlank(message = "회원 비밀번호는 필수입니다.") String password
) { }




