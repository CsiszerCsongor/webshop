package cubix.kh.exam.userservice.security.service;

import cubix.kh.exam.userservice.entity.UserEntity;
import cubix.kh.exam.userservice.enums.UserStatusEnum;
import cubix.kh.exam.userservice.exception.TokenInvalidException;
import cubix.kh.exam.userservice.util.UserEntityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    public static final String ROLE_ENTRY = "roles";
    public static final String USER_ID = "user_id";
    public static final String AUTHORITY_PREFIX = "ROLE_";
    public static final String USER_STATUS = "user_status";
    private final Key signingKey;

    private final Long expirationDuration;

    public JwtService(
            @Value("${jwt.token.secret.key}")
            final String secretKey,
            @Value("${jwt.token.expiration.duration}")
            final Long expirationDuration
    ) {
        this.expirationDuration = expirationDuration;

        signingKey = getSignInKey(secretKey);
    }

    private Key getSignInKey(final String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long extractUserId(final Claims claims) {
        return ((Integer) claims.get(USER_ID)).longValue();
    }

    public String extractUsername(final Claims claims) {
        return extractClaim(claims, Claims::getSubject);
    }

    public <T> T extractClaim(final Claims claims, final Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }

    public Claims validateToken(final String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
    }

    public void validateToken(final String token, final byte[] keyString) {
        try {
            Key key = Keys.hmacShaKeyFor(keyString);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            throw new TokenInvalidException("Invalid token!", e);
        }
    }

    public Claims extractAllClaimsWithoutSecretKey(final String token) {
        final String tokenWithoutSignature = token.substring(0, token.lastIndexOf('.') + 1);
        return Jwts.parserBuilder().build().parseClaimsJwt(tokenWithoutSignature).getBody();
    }

    public UserStatusEnum extractUserStatus(final Claims claims) {
        return UserStatusEnum.valueOf((String) claims.get(USER_STATUS));
    }

    public TokenInformation generateToken(
            final UserEntity userEntity
    ) {
        return generateToken(userEntity, signingKey);
    }

    private TokenInformation generateToken(
            final UserEntity userEntity, final Key secretKey
    ) {
        final Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtService.USER_ID, userEntity.getId());
        extraClaims.put(JwtService.USER_STATUS, userEntity.getStatus());
        extraClaims.put(JwtService.ROLE_ENTRY, UserEntityUtil.getUserRoles(userEntity));

        final Date expirationDate = new Date(System.currentTimeMillis() + expirationDuration);
        final String token = Jwts.builder()
                                 .setClaims(extraClaims)
                                 .setSubject(userEntity.getUsername())
                                 .setIssuedAt(new Date(System.currentTimeMillis()))
                                 .setExpiration(expirationDate)
                                 .signWith(secretKey, SignatureAlgorithm.HS256)
                                 .compact();
        return new TokenInformation(token, expirationDate);
    }
}
