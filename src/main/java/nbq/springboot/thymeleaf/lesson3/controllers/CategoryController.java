package nbq.springboot.thymeleaf.lesson3.controllers;

import nbq.springboot.thymeleaf.lesson3.models.Category;
import nbq.springboot.thymeleaf.lesson3.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepo;

    @RequestMapping("/categories")
    public String listCategories(Model model){
        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("categories", listCategories);
        return "ListCategories";

    }

    @GetMapping("/categories/new")
    public String showCreateCategoryForm(Model model){
        model.addAttribute("category",new Category());
        return "categoryForm";
    }

    @PostMapping("/categories/save")
    public String createCategory(Category category){
        categoryRepo.save(category);
        return "redirect:/categories";
    }
}
