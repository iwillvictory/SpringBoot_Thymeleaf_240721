package nbq.springboot.thymeleaf.lesson3.repositories;

import nbq.springboot.thymeleaf.lesson3.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
