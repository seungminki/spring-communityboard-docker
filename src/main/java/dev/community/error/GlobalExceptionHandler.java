package dev.community.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * Developer Custom Exception: 직접 정의한 RestApiException 에러 클래스에 대한 예외 처리
	 */
	@ExceptionHandler(RestApiException.class)
	protected ResponseEntity<ErrorResponse> handleCustomException(RestApiException ex) {
		ErrorCode errorCode = ex.getErrorCode();
		return handleExceptionInternal(errorCode);
	}

	// handleExceptionInternal() 메소드를 오버라이딩해 응답 커스터마이징
	private ResponseEntity<ErrorResponse> handleExceptionInternal(ErrorCode errorCode) {
		return ResponseEntity
			.status(errorCode.getHttpStatus().value())
			.body(new ErrorResponse(errorCode));
	}
}
