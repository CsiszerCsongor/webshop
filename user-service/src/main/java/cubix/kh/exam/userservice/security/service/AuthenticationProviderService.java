package cubix.kh.exam.userservice.security.service;

import cubix.kh.exam.userservice.entity.UserEntity;
import cubix.kh.exam.userservice.repository.UserEntityRepository;
import cubix.kh.exam.userservice.security.SpecializedUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationProviderService {

    private static final String CURRENT_ENCODER = "argon2id";
    private final UserEntityRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> createUserDetails(userRepository.findIdentityByUsername(username)
                                                           .orElseThrow(() -> new UsernameNotFoundException(
                                                               "User not found!")));
    }

    private SpecializedUserDetails createUserDetails(UserEntity userEntity) {
        Set<String> roles = userEntity.getRoles()
                                      .stream()
                                      .map(rolesEntity -> JwtService.AUTHORITY_PREFIX + rolesEntity.getRole().name())
                                      .collect(Collectors.toUnmodifiableSet());

        return new SpecializedUserDetails(userEntity.getId(),
                                          userEntity.getUsername(),
                                          userEntity.getPassword(),
                                          userEntity.getStatus(),
                                          roles);

    }

    @Bean
    public AuthenticationProvider authenticationProvider(
        final PasswordEncoder passwordEncoder, final UserDetailsService userDetailsService
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        final Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(CURRENT_ENCODER, Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());

        return new DelegatingPasswordEncoder(CURRENT_ENCODER, encoders);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
