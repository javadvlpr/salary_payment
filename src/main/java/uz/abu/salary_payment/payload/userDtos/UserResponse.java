package uz.abu.salary_payment.payload.userDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.abu.salary_payment.entity.User;
import uz.abu.salary_payment.payload.workerDtos.WorkerResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String username;
    private String role;
    private WorkerResponse worker;
    private LocalDateTime createdAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .worker(WorkerResponse.from(user.getWorker()))
                .createdAt(user.getCreatedAt())
                .build();
    }
}
