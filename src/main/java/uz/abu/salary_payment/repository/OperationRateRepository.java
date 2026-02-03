package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.abu.salary_payment.entity.OperationRate;

@Repository
public interface OperationRateRepository extends JpaRepository<OperationRate, Long> {
}
