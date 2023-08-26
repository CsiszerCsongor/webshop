package cubix.kh.exam.userservice.service;

import cubix.kh.exam.userservice.dto.AuthenticationRequestDTO;
import cubix.kh.exam.userservice.dto.AuthenticationResponseDTO;
import cubix.kh.exam.userservice.dto.ProfileResponseDTO;
import cubix.kh.exam.userservice.entity.UserEntity;
import cubix.kh.exam.userservice.repository.UserEntityRepository;
import cubix.kh.exam.userservice.security.service.JwtService;
import cubix.kh.exam.userservice.security.service.TokenInformation;
import cubix.kh.exam.userservice.util.DefaultConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserEntityRepository userRepository;

    public AuthenticationResponseDTO authenticate(final AuthenticationRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(),
                                                                                   request.password()));
        final UserEntity userEntity = userRepository.findByUsername(request.username()).orElseThrow();

        final TokenInformation tokenInformation = jwtService.generateToken(userEntity);

        final ProfileResponseDTO profile = getUserProfile(userEntity);

        return new AuthenticationResponseDTO(tokenInformation.token(),
                                             tokenInformation.expirationTime()
                                                             .toInstant()
                                                             .atZone(DefaultConstants.DEFAULT_ZONE_ID)
                                                             .toOffsetDateTime(),
                                             profile);
    }

    private static ProfileResponseDTO getUserProfile(final UserEntity userEntity) {
        return new ProfileResponseDTO(userEntity.getId(),
                                      userEntity.getUsername(),
                                      userEntity.getEmail(),
                                      userEntity.getStatus(),
                                      userEntity.getRoles()
                                                .stream()
                                                .map(roleEnum -> roleEnum.getRole().name())
                                                .collect(Collectors.toSet()));
    }

}
