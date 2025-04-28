package dev.community.controller;

import dev.community.dto.LoginRequestDto;
import dev.community.dto.MemberRequestDto;
import dev.community.dto.MemberResponseDto;
import dev.community.service.MemberService;
import dev.community.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
class MemberController {

	private final JwtUtil jwtUtil;
	private final MemberService memberService;

	@Operation(description = "멤버 생성")
	@PostMapping("")
	MemberResponseDto newMember(@RequestBody @Valid MemberRequestDto memberRequestDto) {
		return memberService.join(memberRequestDto);
	}

	@Operation(description = "멤버 로그인")
	@PostMapping("/login")
	public String login(@RequestBody @Valid LoginRequestDto req) {
		LoginRequestDto member = memberService.login(req.getEmail(), req.getPassword());
		return jwtUtil.createJwt(member.getEmail()); // 이메일 기준으로 토큰 발행
	}

//	@Operation(description = "멤버 로그아웃")
//	@PostMapping("/logout")
//	public void logout(Principal principal) {
//		memberService.logout();
//	}

	@Operation(description = "멤버 정보 수정")
	@PutMapping("/{id}")
	MemberResponseDto updateMember(Principal principal, @RequestBody @Valid MemberRequestDto MemberRequestDto) {
		return memberService.updateName(principal.getName(), MemberRequestDto.getName());
	}

	@Operation(description = "멤버 삭제")
	@DeleteMapping("/{id}")
	void deleteMember(Principal principal) {
		memberService.deleteMember(principal.getName());
	}

}
