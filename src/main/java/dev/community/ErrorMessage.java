package dev.community;

public enum ErrorMessage {
	INVALID_BOARD_ID("해당 글이 존재하지 않습니다."),
	INVALID_MEMBER_ID("해당 멤버가 존재하지 않습니다."),
	INVALID_COMMENT_ID("해당 댓글이 존재하지 않습니다."),
	INVALID_TOKEN("해당 토큰이 유효하지 않습니다."),
	NOT_MATCHES_ID_AND_PW("아이디 또는 비밀번호가 올바르지 않습니다."),
	ALREADY_EXITS_MEMBER("이미 존재하는 회원입니다.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String format(Object... args) {
		return String.format(message, args);
	}

}
