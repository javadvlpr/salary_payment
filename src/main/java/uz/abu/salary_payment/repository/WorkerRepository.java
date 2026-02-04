package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.abu.salary_payment.entity.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker,Long> {
    @Query(nativeQuery = true, value = """
            select *
            from worker
            where is_active = true
            limit :per_page offset :offset
            """)
    List<Worker> findAll(Integer per_page, Integer offset);

    Optional<Worker> findByIdAndIsActiveTrue(Long id);
}
