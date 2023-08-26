package cubix.kh.exam.userservice.configuration;

import cubix.kh.exam.userservice.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.cors(withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(withDefaults())
            .httpBasic(withDefaults())
            .logout(withDefaults())
            .authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui.html/**",
                                                                "/swagger-ui/**",
                                                                "/v3/api-docs/**")
                                               .permitAll()
                                               .requestMatchers("/status/healthCheck")
                                               .permitAll()
                                               .requestMatchers("/authentication/login",
                                                                "/authentication/renew-access-token",
                                                                "/authentication/invalidate-refresh-tokens",
                                                                "/authentication/forgotten-password",
                                                                "/authentication/forgotten-password/confirmation",
                                                                "/registration")
                                               .permitAll()
                                               .anyRequest()
                                               .authenticated())
            .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
