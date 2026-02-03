package uz.abu.salary_payment.payload;

import lombok.*;
import uz.abu.salary_payment.entity.Worker;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerResponse {
    private String fullName;
    private boolean isActive;
    public static  WorkerResponse from(Worker worker)
    {
        if (worker == null) return null;
        return WorkerResponse.builder()
                .fullName(worker.getFullName())
                .isActive(worker.isActive())
                .build();
    }
}
