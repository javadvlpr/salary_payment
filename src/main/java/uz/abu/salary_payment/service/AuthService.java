package uz.abu.salary_payment.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {
    UserDetails loadUserByEmail(String username) throws UsernameNotFoundException;

}
