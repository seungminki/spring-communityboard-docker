package dev.community.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

	private final MemberService memberService;

	@GetMapping
	public List<Member> getMembers() {
		return memberService.getMembers();
	}

	@PostMapping
	public Member createMember(@RequestBody @Valid Member member) {
		return member;
	}

	@GetMapping("/{id}")
	public Member getMember(@PathVariable Long id) {
		return null;
	}

	@PutMapping("/{id}")
	public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteMember(@PathVariable Long id) {

	}
}
