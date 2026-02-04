package uz.abu.salary_payment.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationRateRequest {
    private String operationType;
    private Long productId;
    private Double pricePerUnit;
}
