package uz.abu.salary_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.abu.salary_payment.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from users limit :perPage offset :offset")
    List<User> findAll(int offset, Integer perPage);
}
