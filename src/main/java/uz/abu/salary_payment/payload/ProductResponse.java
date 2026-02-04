package uz.abu.salary_payment.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.abu.salary_payment.entity.Product;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private LocalDateTime updatedAt;
        public static ProductResponse from(Product product)
        {
            return ProductResponse.builder()
                    .name(product.getName())
                    .updatedAt(product.getUpdatedAt())
                    .build();
        }
}
