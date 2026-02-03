package uz.abu.salary_payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import java.nio.file.AccessDeniedException;

public interface AuthenticationUserService {
    void authenticate(Claims claims, HttpServletRequest request) throws AccessDeniedException, JsonProcessingException;

}
