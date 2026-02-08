package uz.abu.salary_payment.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.abu.salary_payment.entity.WorkRecord;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkRecordResponse {
    private Long id;
    private WorkerResponse worker;
    private OperationRateResponse operationRate;
    private Integer quantity;
    private Integer totalAmount;
    private LocalDateTime createdAt;

     public static WorkRecordResponse from(WorkRecord workRecord) {
         if (workRecord == null) return null;
         return WorkRecordResponse.builder()
                 .id(workRecord.getId())
                 .worker(WorkerResponse.from(workRecord.getWorker()))
                 .operationRate(OperationRateResponse.from(workRecord.getOperationRate()))
                 .quantity(workRecord.getQuantity())
                 .totalAmount(workRecord.getQuantity() * workRecord.getOperationRate().getPricePerUnit())
                 .createdAt(workRecord.getCreatedAt())
                 .build();
     }
}
