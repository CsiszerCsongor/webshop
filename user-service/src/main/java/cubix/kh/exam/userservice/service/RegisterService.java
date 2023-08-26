package cubix.kh.exam.userservice.service;

import cubix.kh.exam.userservice.dto.RegisterResponseDTO;
import cubix.kh.exam.userservice.dto.RegisterUserRequestDTO;

public interface RegisterService {

    RegisterResponseDTO register(final RegisterUserRequestDTO dto);
}
