package uz.abu.salary_payment.payload;

import lombok.*;
import uz.abu.salary_payment.entity.Worker;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerResponse {
    private Long id;
    private String fullName;
    private boolean isActive;
    private LocalDateTime createdAt;
    public static  WorkerResponse from(Worker worker)
    {
        if (worker == null) return null;
        return WorkerResponse.builder()
                .id(worker.getId())
                .fullName(worker.getFullName())
                .isActive(worker.getIsActive())
                .createdAt(worker.getCreatedAt())
                .build();
    }
}
