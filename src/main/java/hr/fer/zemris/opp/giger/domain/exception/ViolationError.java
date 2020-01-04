package hr.fer.zemris.opp.giger.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViolationError {

	private String path;
	private Integer code;
	private String message;

	public ViolationError(int code, String message) {
		this.path = "";
		this.code = code;
		this.message = message;
	}
}
