package nbq.springboot.thymeleaf.lesson3.repositories;

import nbq.springboot.thymeleaf.lesson3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
