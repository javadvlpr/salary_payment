package uz.abu.salary_payment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "work_record")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
    @ManyToOne
    @JoinColumn(name = "operation_rate_id")
    private OperationRate operationRate;
    @NonNull
    private Integer quantity;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Boolean isActive;
}
