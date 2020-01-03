package hr.fer.zemris.opp.giger.domain.exception;

import lombok.Getter;

@Getter
public class GigerException extends RuntimeException {

	private final ErrorCode errorCode;

	public GigerException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public GigerException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}
}
