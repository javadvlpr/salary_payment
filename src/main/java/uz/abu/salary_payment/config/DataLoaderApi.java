package uz.abu.salary_payment.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.abu.salary_payment.entity.User;
import uz.abu.salary_payment.entity.enums.UserRole;
import uz.abu.salary_payment.repository.UserRepository;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoaderApi implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String modeType;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals("create", modeType)) {
            addAdmin();
        }
    }
    private void addAdmin() {
        userRepository.save(
                User.builder()
                        .username(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .role(UserRole.ADMIN)
                        .build()
        );
    }
}
