package uz.abu.salary_payment.service.impl;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.entity.User;
import uz.abu.salary_payment.service.JwtService;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.access.key}")
    private String accessTokenKey;

    @Value("${jwt.access.expiredAt}")
    private Long accessTokenExpired;

    @Value("${jwt.refresh.key}")
    private String refreshTokenKey;

    @Value("${jwt.refresh.expiredAt}")
    private Long refreshTokenExpired;

    @Override
    public String generateAccessToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, accessTokenKey)
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenExpired))
                .addClaims(Map.of(
                        "role", user.getRole().name()
                ))
                .compact();
    }

    @Override
    public Jws<Claims> extractJwtToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(accessTokenKey)
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw e;
        }
    }

    @Override
    public String generateRefreshToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, refreshTokenKey)
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenExpired))
                .addClaims(Map.of(
                        "role", user.getRole().name()
                ))
                .compact();
    }
    @Override
    public Jws<Claims> extractRefreshToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(refreshTokenKey)
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Refresh token expired", e);
        }
    }
}
