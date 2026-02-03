package uz.abu.salary_payment.service;

import uz.abu.salary_payment.payload.JwtResponse;
import uz.abu.salary_payment.payload.UserLoginRequest;
import uz.abu.salary_payment.payload.UserResponse;

import java.util.List;

public interface UserService {

    JwtResponse login(UserLoginRequest userLoginRequest);

    UserResponse getCurrentUser(String username);

    List<UserResponse> getAll(Integer per_page, Integer page);

    JwtResponse refreshAccessToken(String refreshToken);
}
