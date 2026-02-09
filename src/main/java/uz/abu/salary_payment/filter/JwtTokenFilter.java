package uz.abu.salary_payment.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.abu.salary_payment.service.AuthenticationUserService;
import uz.abu.salary_payment.service.JwtService;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final AuthenticationUserService authenticationUserService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // ✅ OPTIONS requestlarni o'tkazib yuborish (CORS preflight)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");

        // ✅ Token yo'q bo'lsa, davom ettirish
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.debug("No JWT token found in request headers");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorization.substring(7); // "Bearer " ni olib tashlash

            log.debug("Processing JWT token");
            Jws<Claims> claimsJws = jwtService.extractJwtToken(token);

            String username = claimsJws.getBody().getSubject();
            String role = (String) claimsJws.getBody().get("role");

            log.info("JWT Token - Username: {}, Role: {}", username, role);

            // ✅ Authenticate qilish
            authenticationUserService.authenticate(claimsJws.getBody(), request);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth != null && auth.isAuthenticated()) {
                log.info("User authenticated successfully: {}", username);
                log.debug("Authorities: {}", auth.getAuthorities());
            } else {
                log.warn("Authentication failed for user: {}", username);
            }

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Token expired\"}");
            return;
        } catch (io.jsonwebtoken.SignatureException e) {
            log.error("JWT signature validation failed: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Invalid token signature\"}");
            return;
        } catch (Exception e) {
            log.error("JWT token validation error: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Invalid token\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}