package dev.community.auth;

import dev.community.ErrorMessage;
import dev.community.member.controller.dto.MemberLoginRequest;
import dev.community.member.entity.Member;
import dev.community.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final MemberJpaRepository memberJpaRepository;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;

	public String login(MemberLoginRequest request) {
		validateAccessEmail(request.email());

		Member member = memberJpaRepository.findByEmail(request.email());

		if (!member.isPasswordMatch(request.password(), passwordEncoder)) {
			throw new IllegalArgumentException(ErrorMessage.NOT_MATCHES_PASSWORD.getMessage());
		}

		return jwtUtil.createJwt(request.email());
	}

	private void validateAccessEmail(String email) {
		if (!memberJpaRepository.existsByEmail(email)) {
			throw new IllegalArgumentException(ErrorMessage.NOT_FOUND_MEMBER.getMessage());
		}
	}
}
