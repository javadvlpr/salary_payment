package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.abu.salary_payment.entity.Worker;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker,Long> {
    List<Worker> findAllByActiveTrue();

}
