package uz.abu.salary_payment.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerCreateResponse {
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private boolean isActive;
    private LocalDateTime createdAt;
}
