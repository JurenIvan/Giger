package hr.fer.zemris.opp.giger.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ViolationError {

	private String path;
	private int code;
	private String message;

	public ViolationError(int code, String message) {
		this.path = "";
		this.code = code;
		this.message = message;
	}
}
