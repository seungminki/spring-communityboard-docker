package dev.community.controller;

import dev.community.dto.member.LoginRequestDto;
import dev.community.dto.member.LoginResponseDto;
import dev.community.dto.member.CreateMemberRequestDto;
import dev.community.dto.member.MemberResponseDto;
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
	MemberResponseDto newMember(@RequestBody @Valid CreateMemberRequestDto memberRequestDto) {
		return memberService.join(memberRequestDto);
	}

	@Operation(description = "멤버 로그인")
	@PostMapping("/login")
	public String login(@RequestBody @Valid LoginRequestDto req) {
		LoginResponseDto member = memberService.login(req.email(), req.password());
		return jwtUtil.createJwt(member.email());
	}

//	@Operation(description = "멤버 로그아웃")
//	@PostMapping("/logout")
//	public void logout(Principal principal) {
//		memberService.logout();
//	}

	@Operation(description = "멤버 정보 수정")
	@PutMapping("/{id}")
	MemberResponseDto updateMember(Principal principal, @RequestBody @Valid CreateMemberRequestDto MemberRequestDto) {
		return memberService.updateName(principal.getName(), MemberRequestDto.name());
	}

	@Operation(description = "멤버 삭제")
	@DeleteMapping("/{id}")
	void deleteMember(Principal principal) {
		memberService.deleteMember(principal.getName());
	}

}
