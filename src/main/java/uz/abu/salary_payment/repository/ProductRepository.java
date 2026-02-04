package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.abu.salary_payment.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = """
            SELECT *
            FROM product
            WHERE is_active = true
            ORDER BY created_at DESC
            limit :per_page offset :offset
            """)
    List<Product> findAll(Integer per_page, Integer offset);

    Optional<Product> findByIdAndIsActiveTrue(Long id);
}
