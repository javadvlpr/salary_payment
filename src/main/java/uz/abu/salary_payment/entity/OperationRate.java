package uz.abu.salary_payment.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.abu.salary_payment.entity.enums.OperationType;

@Entity(name = "operation_rate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OperationRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Double pricePerUnit;
}
