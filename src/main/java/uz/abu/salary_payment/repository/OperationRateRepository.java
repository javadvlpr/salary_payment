package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.abu.salary_payment.entity.OperationRate;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRateRepository extends JpaRepository<OperationRate, Long> {
    Optional<OperationRate> findByIdAndIsActiveTrue(Long id);
    @Query(nativeQuery = true, value = """
            SELECT *
            FROM operation_rate
            WHERE is_active = true
            ORDER BY updated_at DESC
            OFFSET :offset LIMIT :per_page ;
            """)
    List<OperationRate> findAll(Integer per_page, Integer offset);
}
