package uz.abu.salary_payment.service;

import uz.abu.salary_payment.entity.User;
import uz.abu.salary_payment.payload.JwtResponse;
import uz.abu.salary_payment.payload.UserLoginRequest;
import uz.abu.salary_payment.payload.UserResponse;

import java.util.List;
import java.util.Map;

public interface UserService {
    User save(User user);
    JwtResponse login(UserLoginRequest userLoginRequest);
    List<UserResponse> getAll(Integer per_page, Integer page);
    List<User> getAllUsersEntity(Integer per_page, Integer page);
    JwtResponse refreshAccessToken(String refreshToken);
    Map<String,String> generateUsernameAndPassword(String fullName);
    void deleteUser(Long id);
    User getUserByWorkerId(Long workerId);

}
