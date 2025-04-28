package dev.community.controller;

import dev.community.entity.Member;
import dev.community.service.MemberService;
import dev.community.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
class MemberController {

	private final JwtUtil jwtUtil;
	private final MemberService memberService;

	@PostMapping("/new")
	@Operation(description = "멤버 생성")
	@PostMapping("")
	Member newMember(@RequestBody Member newMember) {
		return memberService.join(newMember);
	}

//	@Operation(description = "멤버 조회")
//	@GetMapping("/{id}")
//	public List<MemberResponseDto> members() { return memberService.findMembers(); }

	@Operation(description = "멤버 로그인")
	@PostMapping("/login")
	public String login(@RequestBody Member req) {

	@Operation(description = "멤버 로그아웃")
	@PostMapping("/logout")
	public void logout(Principal principal) {
		memberService.logout();
	}

	@PutMapping("/update")
	Member updateMember(Principal principal, @RequestBody Member member) {
	@Operation(description = "멤버 정보 수정")
	@PutMapping("/{id}")
		return memberService.updateName(principal.getName(), member.getName());
	}

	@DeleteMapping("/delete")
	@Operation(description = "멤버 삭제")
	@DeleteMapping("/{id}")
	void deleteMember(Principal principal) {
		memberService.deleteMember(principal.getName());
	}

}
