package nbq.springboot.thymeleaf.lesson3;


import nbq.springboot.thymeleaf.lesson3.models.Category;
import nbq.springboot.thymeleaf.lesson3.repositories.CategoryRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository categoryRepo;

    @Test
    public void testCreateCategory(){
        Category category = categoryRepo.save(new Category("Electronics"));
        assertThat(category.getId()).isGreaterThan(0);
    }
    @Test
    public void testEditCategory(){

    }

    @Test
    public void testFindCategory(){

    }


}
