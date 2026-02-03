package uz.abu.salary_payment.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.common.AuthenticationException;
import uz.abu.salary_payment.common.DataNotFoundException;
import uz.abu.salary_payment.entity.User;
import uz.abu.salary_payment.payload.JwtResponse;
import uz.abu.salary_payment.payload.UserLoginRequest;
import uz.abu.salary_payment.payload.UserResponse;
import uz.abu.salary_payment.repository.UserRepository;
import uz.abu.salary_payment.service.JwtService;
import uz.abu.salary_payment.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public JwtResponse login(UserLoginRequest userLoginRequest) {
        log.info("Log: {}", userLoginRequest);

        User user = userRepository.findByUsername(userLoginRequest.getUsername()).orElseThrow(
                () -> new AuthenticationException("username or password incorrect"));

        checkPassword(userLoginRequest.getPassword(), user.getPassword());

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return JwtResponse.from(accessToken, refreshToken, UserResponse.from(user));
    }

    @Override
    public UserResponse getCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User not found!"));

        return UserResponse.from(user);
    }

    @Override
    public List<UserResponse> getAll(Integer per_page, Integer page) {
        int offset = (page - 1) * per_page;
        List<User> users = userRepository.findAll(offset, per_page);

        return users.stream().map(UserResponse::from).toList();
    }

    @Override
    public JwtResponse refreshAccessToken(String refreshToken) {
        Jws<Claims> claimsJws = jwtService.extractRefreshToken(refreshToken);
        String username = claimsJws.getBody().getSubject();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        return JwtResponse.from(jwtService.generateAccessToken(user),
                refreshToken,
                UserResponse.from(user));
    }
    private void checkPassword(String dtoPassword, String daoPassword) {
        if (!passwordEncoder.matches(dtoPassword, daoPassword))
            throw new DataNotFoundException("username or password incorrect");

    }
}
