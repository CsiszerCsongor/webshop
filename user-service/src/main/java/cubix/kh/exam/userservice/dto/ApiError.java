package cubix.kh.exam.userservice.dto;

import cubix.kh.exam.userservice.model.Response;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.Set;

// Todo: Customize the error object
@Getter
public final class ApiError implements Response {

    @Serial
    private static final long serialVersionUID = 4203722646400530336L;

    private final HttpStatus status;
    private final String defaultMessage;
    private final Set<String> errors;

    public ApiError(final HttpStatus status, final String defaultMessage, final String error) {
        this(status, defaultMessage, Set.of(error));
    }

    public ApiError(final HttpStatus status, final String defaultMessage, final Set<String> errors) {
        super();

        this.status = status;
        this.defaultMessage = defaultMessage;
        this.errors = Set.copyOf(errors);
    }

}
