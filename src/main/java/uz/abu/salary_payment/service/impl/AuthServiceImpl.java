package uz.abu.salary_payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.common.DataNotFoundException;
import uz.abu.salary_payment.repository.UserRepository;
import uz.abu.salary_payment.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByEmail(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("user not found"));
    }
}
