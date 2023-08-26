package cubix.kh.exam.userservice.service;

import cubix.kh.exam.userservice.dto.AuthenticationRequestDTO;
import cubix.kh.exam.userservice.dto.AuthenticationResponseDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO authenticate(final AuthenticationRequestDTO request);
}
