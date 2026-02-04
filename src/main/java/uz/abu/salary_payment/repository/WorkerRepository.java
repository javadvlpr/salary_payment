package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.abu.salary_payment.entity.Worker;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {
    @Query(nativeQuery = true, value = """
            SELECT *
            FROM worker
            WHERE is_active = true
            ORDER BY created_at DESC
            limit :per_page offset :offset
            """)
    List<Worker> findAll(Integer per_page, Integer offset);

    Optional<Worker> findByIdAndIsActiveTrue(Long id);
}
