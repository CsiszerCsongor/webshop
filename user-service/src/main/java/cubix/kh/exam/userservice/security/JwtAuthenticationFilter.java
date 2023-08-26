package cubix.kh.exam.userservice.security;

import cubix.kh.exam.userservice.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final int BEARER_PREFIX_LENGTH = BEARER.length();
    public static final String JWT_TOKEN_EXPIRED_ERROR = "JWT token expired!";
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            final String jwt = authHeader.substring(BEARER_PREFIX_LENGTH);
            Claims claims;
            try {
                claims = jwtService.validateToken(jwt);
            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(JWT_TOKEN_EXPIRED_ERROR);
                return;
            }

            if ((new Date()).before(claims.getExpiration())) {
                UserDetails userDetails = createUserDetailFromJwtToken(claims);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private UserDetails createUserDetailFromJwtToken(Claims claims) {
        return new SpecializedUserDetails(jwtService.extractUserId(claims),
                                          jwtService.extractUsername(claims),
                                          null,
                                          jwtService.extractUserStatus(claims),
                                          (ArrayList<String>) jwtService.extractClaim(claims,
                                                                                      claim -> claim.get(JwtService.ROLE_ENTRY)));
    }

}
