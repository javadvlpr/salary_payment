package uz.abu.salary_payment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import uz.abu.salary_payment.entity.enums.OperationType;

import java.time.LocalDateTime;

@Entity(name = "operation_rate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OperationRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Double pricePerUnit;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
