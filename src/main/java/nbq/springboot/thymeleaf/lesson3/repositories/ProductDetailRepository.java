package nbq.springboot.thymeleaf.lesson3.repositories;

import nbq.springboot.thymeleaf.lesson3.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
}
