package hr.fer.zemris.opp.giger.web.rest.handler;

import hr.fer.zemris.opp.giger.domain.exception.ApiError;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.domain.exception.ViolationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.EXCEPTION;
import static java.util.List.of;

@ControllerAdvice
public class RestExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(GigerException.class)
    protected ResponseEntity<Object> handlePaspoortException(GigerException ex) {
        log.debug("Giger business exception");
        return new ResponseEntity<>(
                new ApiError(EXCEPTION.getCode(), EXCEPTION.getMessage(), of(new ViolationError(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage()))),
                new HttpHeaders(),
                ex.getErrorCode().getStatus()
        );
    }

}
