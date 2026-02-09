package uz.abu.salary_payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.abu.salary_payment.payload.JwtResponse;
import uz.abu.salary_payment.payload.UserLoginRequest;
import uz.abu.salary_payment.service.UserService;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {
    private final UserService userService;

    @Operation(description = "Login")
    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        log.info("Login attempt for user: {}", userLoginRequest.getUsername());
        JwtResponse response = userService.login(userLoginRequest);
        log.info("Login successful for user: {}", userLoginRequest.getUsername());
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Refresh token")
    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestParam String refreshToken) {
        log.info("Token refresh attempt");
        JwtResponse response = userService.refreshAccessToken(refreshToken);
        log.info("Token refreshed successfully");
        return ResponseEntity.ok(response);
    }
}