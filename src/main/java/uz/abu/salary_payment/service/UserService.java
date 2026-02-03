package uz.abu.salary_payment.service;

import uz.abu.salary_payment.entity.User;
import uz.abu.salary_payment.payload.JwtResponse;
import uz.abu.salary_payment.payload.userDtos.UserLoginRequest;
import uz.abu.salary_payment.payload.userDtos.UserResponse;

import java.util.List;
import java.util.Map;

public interface UserService {
    User save(User user);

    JwtResponse login(UserLoginRequest userLoginRequest);

    UserResponse getCurrentUser(String username);

    List<UserResponse> getAll(Integer per_page, Integer page);

    JwtResponse refreshAccessToken(String refreshToken);
    Map<String,String> generateUsernameAndPassword(String fullName);
}
