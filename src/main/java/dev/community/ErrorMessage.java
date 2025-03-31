package dev.community;

public enum ErrorMessage {
	INVALID_BOARD_ID("해당 글이 존재하지 않습니다."),
	INVALID_MEMBER_ID("해당 멤버가 존재하지 않습니다."),
	INVALID_COMMENT_ID("해당 댓글이 존재하지 않습니다.");

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
