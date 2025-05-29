package dev.community.member.service;

import dev.community.ErrorMessage;
import dev.community.auth.JwtUtil;
import dev.community.member.controller.dto.MemberCreateRequest;
import dev.community.member.controller.dto.MemberLoginRequest;
import dev.community.member.controller.dto.MemberUpdateRequest;
import dev.community.member.entity.Member;
import dev.community.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;


@RequiredArgsConstructor
@Service
public class MemberCommandService {

	private final MemberJpaRepository memberJpaRepository;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;

	public Long create(MemberCreateRequest request) {
		Member member = Member.builder()
			.email(request.email())
			.name(request.name())
			.password(request.password())
			.build();

		member.encodePassword(passwordEncoder);

		memberJpaRepository.save(member);

		return member.getId();
	}

	public Long update(Principal principal, MemberUpdateRequest request) {
		validateAccessEmail(principal.getName());
		validateDuplicateName(request.name());

		Member member = memberJpaRepository.findByEmail(principal.getName());
		member.updateName(request.name());
		memberJpaRepository.save(member);

		return member.getId();
	}

	public void delete(Principal principal) {
		validateAccessEmail(principal.getName());

		Member member = memberJpaRepository.findByEmail(principal.getName());

		memberJpaRepository.delete(member);
	}

	private void validateAccessEmail(String email) {
		if (!memberJpaRepository.existsByEmail(email)) {
			throw new IllegalArgumentException(ErrorMessage.NOT_FOUND_MEMBER.getMessage());
		}
	}

	private void validateDuplicateName(String name) {
		if (memberJpaRepository.existsByName(name)) {
			throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NAME.getMessage());
		}
	}

}
