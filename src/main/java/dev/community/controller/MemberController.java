package dev.community.controller;

import dev.community.entity.Member;
import dev.community.service.MemberService;
import dev.community.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
class MemberController {

	private final JwtUtil jwtUtil;
	private final MemberService memberService;

	@PostMapping("/new")
	Member newMember(@RequestBody Member newMember) {
		return memberService.join(newMember);
	}

	@GetMapping("/all")
	public List<Member> members() { return memberService.findMembers(); }

	@GetMapping("/find")
	public Optional<Member> findMember(@RequestBody Long memberId) {
		return memberService.findOne(memberId);
	}

	@PostMapping("/login")
	public String login(@RequestBody Member req) {

		Member member = memberService.login(req.getEmail(), req.getPassword());
		return jwtUtil.createJwt(member.getEmail());
	}

	@PutMapping("/update")
	Member updateMember(Principal principal, @RequestBody String newName) {
		return memberService.updateName(principal, newName);
	}

	@DeleteMapping("/delete")
	void deleteMember(Principal principal) {
		memberService.deleteMember(principal);
	}

}
