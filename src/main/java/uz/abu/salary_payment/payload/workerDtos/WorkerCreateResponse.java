package uz.abu.salary_payment.payload.workerDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.abu.salary_payment.entity.Worker;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerCreateResponse {
    private String fullName;
    private String username;
    private String password;
    private boolean isActive;
    private LocalDateTime createdAt;
}
