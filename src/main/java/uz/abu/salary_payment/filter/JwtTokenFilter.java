package uz.abu.salary_payment.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.abu.salary_payment.service.AuthenticationUserService;
import uz.abu.salary_payment.service.JwtService;

import java.io.IOException;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final AuthenticationUserService authenticationUserService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.replace("Bearer ", "");
        Jws<Claims> claimsJws = jwtService.extractJwtToken(token);

        System.out.println("Email: " + claimsJws.getBody().getSubject());
        System.out.println("Role from token: " + claimsJws.getBody().get("role"));

        authenticationUserService.authenticate(claimsJws.getBody(), request);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authenticated: " + (auth != null && auth.isAuthenticated()));
        System.out.println("Authorities: " + (auth != null ? auth.getAuthorities() : "null"));

        filterChain.doFilter(request, response);
    }
}
