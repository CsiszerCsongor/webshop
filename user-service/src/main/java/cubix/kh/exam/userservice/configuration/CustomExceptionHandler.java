package cubix.kh.exam.userservice.configuration;

import cubix.kh.exam.userservice.dto.ApiError;
import cubix.kh.exam.userservice.exception.EmailAlreadyInUseException;
import cubix.kh.exam.userservice.exception.RoleNotFoundException;
import cubix.kh.exam.userservice.exception.UsernameAlreadyInUseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    private static final String GENERAL_MESSAGE = "Error happened in server side!";
    private static final String ERROR_OCCURRED = "error occurred";

    @ExceptionHandler({ EmailAlreadyInUseException.class })
    public ResponseEntity<ApiError> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {
        log.error(ex.getMessage(), ex);
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), Collections.emptySet());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ UsernameAlreadyInUseException.class })
    public ResponseEntity<ApiError> handleUsernameAlreadyInUseException(UsernameAlreadyInUseException ex) {
        log.error(ex.getMessage(), ex);
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), Collections.emptySet());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ RoleNotFoundException.class })
    public ResponseEntity<ApiError> handleRoleNotFoundException(RoleNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), Collections.emptySet());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ApiError> handleAll(Exception ex) {
        log.error(ex.getMessage(), ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, GENERAL_MESSAGE, ERROR_OCCURRED);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
