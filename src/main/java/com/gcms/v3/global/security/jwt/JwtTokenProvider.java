package com.gcms.v3.global.security.jwt;

import com.gcms.v3.domain.auth.presentation.data.response.TokenInfoResponseDto;
import com.gcms.v3.global.security.exception.InvalidAuthTokenException;
import com.gcms.v3.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import static com.gcms.v3.global.security.jwt.JwtProperties.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static Key accessTokenkey;
    private static Key refreshtokenkey;
    private final AuthDetailsService authDetailsService;
    private final JwtProperties jwtProperties;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getAccessTokenKey());
        accessTokenkey = Keys.hmacShaKeyFor(keyBytes);

        byte[] refreshKeyBytes = Decoders.BASE64.decode(jwtProperties.getRefreshTokenKey());
        refreshtokenkey = Keys.hmacShaKeyFor(refreshKeyBytes);
    }

    public TokenInfoResponseDto generateToken(String email) {
        return TokenInfoResponseDto.builder()
                .accessToken(generateAccessToken(email))
                .refreshToken(generateRefreshToken(email))
                .accessTokenExpiresIn(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME))
                .refreshTokenExpiresIn(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME))
                .build();
    }

    private String generateAccessToken(String email) {
        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setHeaderParam("typ", GRANT_TYPE)
                .claim(AUTHORITIES, "JWT")
                .setExpiration(accessTokenExpiresIn)
                .signWith(accessTokenkey, SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken(String email) {
        long now = (new Date()).getTime();

        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setHeaderParam("typ", "JWT")
                .signWith(refreshtokenkey, SignatureAlgorithm.HS256)
                .claim(AUTHORITIES, "JWT")
                .setIssuedAt(new Date())
                .setExpiration(refreshTokenExpiresIn)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        if (claims.get(AUTHORITIES) == null) {
            throw new InvalidAuthTokenException();
        }

        UserDetails userDetails = authDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims parseClaims(String assessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(accessTokenkey).build().parseClaimsJws(assessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(accessTokenkey).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new InvalidAuthTokenException();
        } catch (ExpiredJwtException e) {
            throw new InvalidAuthTokenException();
        } catch (UnsupportedJwtException e) {
            throw new InvalidAuthTokenException();
        }
    }

}
