package cubix.kh.exam.userservice.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

public record AuthenticationResponseDTO(String accessToken, OffsetDateTime tokenValidity,
                                        ProfileResponseDTO profile)
        implements Serializable {

}
