package uz.abu.salary_payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.abu.salary_payment.payload.JwtResponse;
import uz.abu.salary_payment.payload.UserLoginRequest;
import uz.abu.salary_payment.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {
    private final UserService userService;

    @Operation(description = "Login")
    @PutMapping("/sign-in")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(userService.login(userLoginRequest));
    }

    @Operation(description = "Refresh token")
    @PutMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestParam String refreshToken) {
        return ResponseEntity.ok(userService.refreshAccessToken(refreshToken));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminTest() {
        return "OK";
    }
}
