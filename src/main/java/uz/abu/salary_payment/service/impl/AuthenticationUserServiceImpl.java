package uz.abu.salary_payment.service.impl;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.service.AuthenticationUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationUserServiceImpl implements AuthenticationUserService {
    @Override
    public void authenticate(Claims claims, HttpServletRequest request) {
        String email = claims.getSubject();

        // DEBUG: Qo'shing
        Object roleFromClaim = claims.get("role");
        System.out.println("Role type: " + (roleFromClaim != null ? roleFromClaim.getClass() : "null"));
        System.out.println("Role value: " + roleFromClaim);

        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(
                email,
                null,
                getRoles(List.of((String) claims.get("role")))
        );

        // DEBUG
        System.out.println("Created authorities: " + authentication.getAuthorities());

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private List<SimpleGrantedAuthority> getRoles(List<String> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }
}
