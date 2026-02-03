package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.abu.salary_payment.entity.WorkRecord;

@Repository
public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {
}
