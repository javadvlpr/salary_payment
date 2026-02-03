package uz.abu.salary_payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    private LocalDate date;
}
