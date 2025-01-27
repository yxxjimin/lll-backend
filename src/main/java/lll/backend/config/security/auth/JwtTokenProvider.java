package lll.backend.config.security.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long tokenValidityInSeconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") final String secretKey,
            @Value("${jwt.token-validity-in-seconds}") final long tokenValidityInSeconds
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String createToken(String subject, List<? extends GrantedAuthority> authorities) {
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + tokenValidityInSeconds);

        List<String> roles = new ArrayList<>();
        roles.add("user");

        return Jwts.builder()
                .setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(secretKey)
                .compact();
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException
                 | MalformedJwtException
                 | SignatureException
                 | ExpiredJwtException | IllegalArgumentException e) {
            throw new BadCredentialsException("Invalid JWT Token");
        }
    }
}
