package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.abu.salary_payment.entity.WorkRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {
    @Query(nativeQuery = true, value = """
            SELECT *
            FROM work_record
            WHERE id = :id AND is_active = true ;
            """)
    Optional<WorkRecord> finByIdAndIsActiveTrue(Long id);
    @Query(nativeQuery = true, value = """
            SELECT *
            FROM work_record
            WHERE is_active = true
            ORDER BY created_at DESC
            OFFSET :offset LIMIT :per_page ;
            """)
    List<WorkRecord> findAll(Integer per_page, Integer offset);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM work_record wr
            JOIN operation_rate or
            ON wr.operation_rate_id = or.id
            WHERE or.operation_type = :name
            AND wr.created_at >= :startDate
            AND wr.is_active = true
            """)
    List<WorkRecord> findAllByCreatedAtAfterAndIsActiveTrue(String name, LocalDate startDate);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM work_record
            WHERE created_at >= :startDate
            AND is_active = true
            """)
    List<WorkRecord> findAllByCreatedAtAfterAndIsActiveTrue(LocalDate startDate);
}
