package uz.abu.salary_payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "worker")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Worker {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String fullName;
    private boolean isActive;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
