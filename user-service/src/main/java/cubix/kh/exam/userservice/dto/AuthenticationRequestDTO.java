package cubix.kh.exam.userservice.dto;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AuthenticationRequestDTO(@Size(min = 5, max = 50) String username,
                                       @Size(min = 5, max = 50) String password) implements Serializable {

}
