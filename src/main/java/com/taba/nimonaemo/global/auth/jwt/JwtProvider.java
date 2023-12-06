package com.taba.nimonaemo.global.auth.jwt;

import com.taba.nimonaemo.global.auth.role.MemberRole;
import com.taba.nimonaemo.global.error.exception.ExpiredTokenException;
import com.taba.nimonaemo.global.error.exception.IllegalTokenTypeException;
import com.taba.nimonaemo.global.error.exception.InvalidTokenException;
import com.taba.nimonaemo.member.model.entity.Member;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationTokenProvider {

    public static final String AUTHORIZATION = "Authorization";

    @Value("${app.auth.jwt.access-expiration}")
    private Duration accessExpiration;

    @Value("${app.auth.jwt.refresh-expiration}")
    private Duration refreshExpiration;

    @Value("${app.auth.jwt.secret-key}")
    private String secretKey;

    @Override
    public String getAccessTokenFromHeader(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("access-token")) {
                    return cookie.getValue();
                }
            }
        }

        String header = request.getHeader(AUTHORIZATION);
        if (header != null) {
            if (!header.toLowerCase().startsWith("bearer ")) {
                throw new IllegalTokenTypeException();
            }
            return header.substring(7);
        }

        return null;
    }

    @Override
    public JwtAuthentication getAuthentication(String accessToken) {
        Jws<Claims> claimsJws = validateAccessToken(accessToken);

        Claims body = claimsJws.getBody();
        Long userId = Long.parseLong((String) body.get("userId"));
        MemberRole memberRole = MemberRole.of((String) body.get("userRole"));

        return new JwtAuthentication(userId, memberRole);
    }

    public AuthenticationToken issue(Member member) {
        return JwtAuthenticationToken.builder()
                .accessToken(createAccessToken(member.getId().toString(), member.getMemberRole()))
                .refreshToken(createRefreshToken())
                .build();
    }

    public AuthenticationToken reissue(String userId, MemberRole memberRole) {
//        String validateRefreshToken = validateRefreshToken(refreshToken);
        String accessToken = createAccessToken(userId, memberRole);

        return JwtAuthenticationToken.builder()
                .accessToken(accessToken)
                .refreshToken("")
                .build();
    }

    private String refreshAccessToken(String accessToken) {
        String userId;
        MemberRole role;

        try {
            Jws<Claims> claimsJws = validateAccessToken(accessToken);
            Claims body = claimsJws.getBody();
            userId = (String) body.get("userId");
            role = MemberRole.of((String) body.get("userRole"));
        } catch (ExpiredJwtException e) {
            userId = (String) e.getClaims().get("userId");
            role = MemberRole.of((String) e.getClaims().get("userRole"));
        }
        return createAccessToken(userId, role);
    }

    private String createAccessToken(String string, MemberRole memberRole) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validity = now.plus(accessExpiration);

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("userId", string);
        payloads.put("userRole", memberRole.getName());

        return Jwts.builder()
                .setSubject("MemberInfo")
                .setClaims(payloads)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(validity.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

    }

    private String createRefreshToken() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validity = now.plus(refreshExpiration);
        return Jwts.builder()
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(validity.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    private Jws<Claims> validateAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(accessToken);
        } catch (IllegalArgumentException e) {
            throw new ExpiredTokenException();
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }
    }

    public String validateRefreshToken(String refreshToken) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(refreshToken);
            return refreshToken;
        } catch (ExpiredJwtException e) {
            return createRefreshToken();
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }
    }
}
