package uz.abu.salary_payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.abu.salary_payment.payload.userDtos.UserResponse;
import uz.abu.salary_payment.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @Operation(description = "Get All Users")
    @PutMapping("/get-all")
    public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam Integer per_page, @RequestParam Integer page) {
        return ResponseEntity.ok(userService.getAll(per_page, page));
    }
}
