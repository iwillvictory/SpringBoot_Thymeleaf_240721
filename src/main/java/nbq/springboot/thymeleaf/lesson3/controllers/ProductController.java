package nbq.springboot.thymeleaf.lesson3.controllers;

import nbq.springboot.thymeleaf.lesson3.models.Category;
import nbq.springboot.thymeleaf.lesson3.models.Product;
import nbq.springboot.thymeleaf.lesson3.models.ProductDetail;
import nbq.springboot.thymeleaf.lesson3.repositories.CategoryRepository;
import nbq.springboot.thymeleaf.lesson3.repositories.ProductDetailRepository;
import nbq.springboot.thymeleaf.lesson3.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductDetailRepository productDetailRepo;

    @ModelAttribute
    public void listCategories(Model model){
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories",categories);
    }

    @RequestMapping("/products")
    public String listProducts(Model model){
        List<Product> listProducts = productRepo.findAll();
        model.addAttribute("products", listProducts);
        return "ListProducts";

    }

    @GetMapping("/products/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product",new Product());
        return "productForm";
    }

    @PostMapping("/products/save")
    public String createProduct(Product product, HttpServletRequest request) {
        String[] idDetails = request.getParameterValues("detailIDs");
        String[] nameDetails = request.getParameterValues("info");
        String[] valueDetails = request.getParameterValues("value");
        if(nameDetails.length > 0 && valueDetails.length > 0){
            for (int i = 0; i < nameDetails.length ; i++) {

                if (!nameDetails[i].trim().isEmpty() && !valueDetails[i].trim().isEmpty()) {
                    if(idDetails != null && idDetails.length > 0){
                        product.addProductDetail(Integer.valueOf(idDetails[i]),nameDetails[i],valueDetails[i]);
                    } else {
                        product.addProductDetail(nameDetails[i],valueDetails[i]);
                    }
                }
            }
        }

        productRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productRepo.getById(id);
        if(product != null){
            model.addAttribute("product",product);
        } else {
            model.addAttribute("product",new Product());
        }
        return "productForm";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepo.deleteById(id);
        return "redirect:/products";
    }

}
