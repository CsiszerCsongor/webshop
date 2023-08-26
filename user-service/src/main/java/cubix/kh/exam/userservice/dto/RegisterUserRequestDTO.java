package cubix.kh.exam.userservice.dto;

import cubix.kh.exam.userservice.validator.ExtendedEmailValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequestDTO(@NotBlank @Size(min = 5, max = 50) String username,
                                     @NotBlank @ExtendedEmailValidator String email,
                                     @NotBlank @Size(min = 5, max = 50) String password) {

}
