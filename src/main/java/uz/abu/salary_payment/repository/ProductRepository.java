package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.abu.salary_payment.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
