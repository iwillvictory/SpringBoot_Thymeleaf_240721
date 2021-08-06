package nbq.springboot.thymeleaf.lesson3.repositories;

import nbq.springboot.thymeleaf.lesson3.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
