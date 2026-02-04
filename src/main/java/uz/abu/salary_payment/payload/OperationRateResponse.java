package uz.abu.salary_payment.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.abu.salary_payment.entity.OperationRate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationRateResponse {
    private Long id;
    private String operationType;
    private Long productId;
    private Double pricePerUnit;

    public static OperationRateResponse from(OperationRate operationRate) {
        return OperationRateResponse.builder()
                .id(operationRate.getId())
                .operationType(operationRate.getOperationType().name())
                .productId(operationRate.getProduct().getId())
                .pricePerUnit(operationRate.getPricePerUnit())
                .build();
    }
}
