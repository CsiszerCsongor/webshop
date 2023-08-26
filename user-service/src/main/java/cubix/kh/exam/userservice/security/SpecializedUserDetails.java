package cubix.kh.exam.userservice.security;

import cubix.kh.exam.userservice.enums.UserStatusEnum;
import cubix.kh.exam.userservice.security.service.JwtService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecializedUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final UserStatusEnum status;
    private final Set<SimpleGrantedAuthority> roles;

    public SpecializedUserDetails(
        final Long id,
        final String username,
        final String password,
        final UserStatusEnum status,
        final Collection<String> roles
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles.stream()
                          .map(role -> new SimpleGrantedAuthority(JwtService.AUTHORITY_PREFIX + role))
                          .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != UserStatusEnum.INACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
