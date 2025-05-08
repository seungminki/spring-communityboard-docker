package dev.community;

import lombok.Getter;

@Getter
public enum ErrorMessage {
	INVALID_BOARD_ID("해당 글이 존재하지 않습니다."),
	INVALID_MEMBER_ID("해당 멤버가 존재하지 않습니다."),
	INVALID_COMMENT_ID("해당 댓글이 존재하지 않습니다."),
	INVALID_TOKEN("해당 토큰이 유효하지 않습니다."),
	INVALID_LIKED_ID("좋아요가 유효하지 않습니다."),

	NOT_MATCHES_ID_AND_PW("아이디 또는 비밀번호가 올바르지 않습니다."),
	ALREADY_EXITS_MEMBER("이미 존재하는 회원입니다."),
	NOT_EMAIL("이메일 형식이 아닙니다."),

	NULL_BOARD_TITLE("글 제목이 NULL 입니다."),
	NULL_BOARD_CONTENT("글 본문이 NULL 입니다."),
	NULL_MEMBER_EMAIL("계정 이메일이 NULL 입니다."),
	NULL_MEMBER_NAME("계정 이름이 NULL 입니다."),
	NULL_MEMBER_PASSWORD("계정 비밀번호가 NULL 입니다."),
	NULL_COMMENT_CONTENT("댓글 본문이 NULL 입니다."),

	DONT_MATCH_USER_LOGINED("로그인한 멤버와 작성자가 일치하지 않습니다.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String format(Object... args) {
		return String.format(message, args);
	}

}
