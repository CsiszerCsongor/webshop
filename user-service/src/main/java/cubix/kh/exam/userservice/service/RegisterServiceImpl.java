package cubix.kh.exam.userservice.service;

import cubix.kh.exam.userservice.dto.RegisterResponseDTO;
import cubix.kh.exam.userservice.dto.RegisterUserRequestDTO;
import cubix.kh.exam.userservice.entity.UserEntity;
import cubix.kh.exam.userservice.enums.RoleEnum;
import cubix.kh.exam.userservice.enums.UserStatusEnum;
import cubix.kh.exam.userservice.exception.EmailAlreadyInUseException;
import cubix.kh.exam.userservice.exception.RoleNotFoundException;
import cubix.kh.exam.userservice.exception.UsernameAlreadyInUseException;
import cubix.kh.exam.userservice.mapper.UserMapper;
import cubix.kh.exam.userservice.repository.RoleEntityRepository;
import cubix.kh.exam.userservice.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserEntityRepository userRepository;
    private final RoleEntityRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public RegisterResponseDTO register(final RegisterUserRequestDTO dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new UsernameAlreadyInUseException("Username already in use!");
        }
        if (userRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyInUseException("Email already in use!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setStatus(UserStatusEnum.ACTIVE);
        user.setRoles(Set.of(roleRepository.findByRole(RoleEnum.CUSTOMER)
                                           .orElseThrow(() -> new RoleNotFoundException(
                                                   "Role not found: " + RoleEnum.CUSTOMER))));

        return userMapper.registeredUserToDTO(userRepository.save(user));
    }

}
