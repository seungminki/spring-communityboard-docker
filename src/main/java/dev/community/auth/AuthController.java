package dev.community.auth;

import dev.community.member.controller.dto.MemberLoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public String loginMember(@RequestBody @Valid MemberLoginRequest request) {
		return authService.login(request);
	}

	@PostMapping("/logout")
	public void logoutMember(Principal principal) {
	}
}
