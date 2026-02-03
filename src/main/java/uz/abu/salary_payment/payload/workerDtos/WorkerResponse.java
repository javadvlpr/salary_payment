package uz.abu.salary_payment.payload.workerDtos;

import lombok.*;
import uz.abu.salary_payment.entity.Worker;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerResponse {
    private String fullName;
    private boolean isActive;
    private LocalDateTime createdAt;
    public static  WorkerResponse from(Worker worker)
    {
        if (worker == null) return null;
        return WorkerResponse.builder()
                .fullName(worker.getFullName())
                .isActive(worker.isActive())
                .createdAt(worker.getCreatedAt())
                .build();
    }
}
