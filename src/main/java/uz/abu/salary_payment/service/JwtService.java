package uz.abu.salary_payment.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import uz.abu.salary_payment.entity.User;

public interface JwtService {
    String generateAccessToken(User user);

    Jws<Claims> extractJwtToken(String token);

    String generateRefreshToken(User user);

    Jws<Claims> extractRefreshToken(String token);
}
