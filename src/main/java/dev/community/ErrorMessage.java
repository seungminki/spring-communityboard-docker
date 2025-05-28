package dev.community;

import lombok.Getter;

@Getter
public enum ErrorMessage {

	// MEMBER
	NOT_FOUND_MEMBER("[ERROR] 회원을 찾을 수 없습니다."),
	NOT_MATCHES_PASSWORD("[ERROR] 회원과 비밀번호가 일치하지 않습니다."),
	DUPLICATE_NAME("[ERROR] 이미 존재하는 이름입니다."),

	// BOARD
	INVALID_BOARD_ID("[ERROR] 존재하지않는 게시글입니다."),
	NOT_MATCHES_MEMBER("[ERROR] 작성자만 수정/삭제할 수 있습니다.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String format(Object... args) {
		return String.format(message, args);
	}

}
