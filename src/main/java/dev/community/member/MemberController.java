package dev.community.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
class MemberController {

	private final MemberService service;

	@GetMapping("/members")
	List<Member> allMembers() {
		return service.getMembers();
	}

	@PostMapping("/members")
	Member newMember(@RequestBody Member newMember) {
		return service.createMember(newMember);
	}

	@GetMapping("/members/{id}")
	Member getMember(@PathVariable Long id) {
		return service.getSingleMember(id);
	}

	@PutMapping("/members/{id}")
	Member updateMember(@PathVariable Long id, @RequestBody Member newMember) {
		return service.updateMember(id, newMember);
	}

	@DeleteMapping("/members/{id}")
	void deleteMember(@PathVariable Long id) {
		service.deleteMember(id);
	}

}
