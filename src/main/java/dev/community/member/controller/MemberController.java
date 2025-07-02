package dev.community.member.controller;

import dev.community.member.controller.dto.MemberCreateRequest;
import dev.community.member.controller.dto.MemberUpdateRequest;
import dev.community.member.service.MemberCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

	private final MemberCommandService memberCommandService;

	@PostMapping
	public Long createMember(@RequestBody @Valid MemberCreateRequest request) {
		return memberCommandService.create(request);
	}


	@PutMapping
	public Long updateMember(@AuthenticationPrincipal Principal principal, @RequestBody @Valid MemberUpdateRequest request) {
		return memberCommandService.update(principal, request);
	}

	@DeleteMapping
	public void deleteMember(@AuthenticationPrincipal Principal principal) {
		memberCommandService.delete(principal);

	}

}
