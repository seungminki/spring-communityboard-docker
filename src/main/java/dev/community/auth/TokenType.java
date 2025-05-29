package dev.community.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {

	AUTHORIZATION_HEADER("Authorization"),
	BEARER_PREFIX("Bearer "),
	BEARER_PREFIX_LEN("7");

	private final String value;
}
