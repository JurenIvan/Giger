package hr.fer.zemris.opp.giger.config.errorHandling;

public class GigerException extends RuntimeException {

    private ErrorCode errorCode;

    public GigerException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
