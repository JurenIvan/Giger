package hr.fer.zemris.opp.giger.domain.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ApiError {

    private final int code;
    private final String message;
    private List<ViolationError> violationErrors;

    public ApiError(int code, String message, List<ViolationError> violationErrors) {
        this.code = code;
        this.message = message;
        this.violationErrors = violationErrors;
    }
}
