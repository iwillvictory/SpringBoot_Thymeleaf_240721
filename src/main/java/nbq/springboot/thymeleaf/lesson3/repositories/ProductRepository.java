package nbq.springboot.thymeleaf.lesson3.repositories;

import nbq.springboot.thymeleaf.lesson3.models.Category;
import nbq.springboot.thymeleaf.lesson3.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
