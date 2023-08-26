package cubix.kh.exam.userservice.dto;

import cubix.kh.exam.userservice.enums.UserStatusEnum;

import java.io.Serializable;
import java.util.Set;

public record ProfileResponseDTO(long userId, String username, String email, UserStatusEnum status,
                                 Set<String> roles) implements Serializable {

}
