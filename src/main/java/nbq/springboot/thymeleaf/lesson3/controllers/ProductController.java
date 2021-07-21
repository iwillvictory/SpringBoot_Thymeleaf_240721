package nbq.springboot.thymeleaf.lesson3.controllers;

import nbq.springboot.thymeleaf.lesson3.models.Category;
import nbq.springboot.thymeleaf.lesson3.models.Product;
import nbq.springboot.thymeleaf.lesson3.models.ProductDetail;
import nbq.springboot.thymeleaf.lesson3.repositories.CategoryRepository;
import nbq.springboot.thymeleaf.lesson3.repositories.ProductDetailRepository;
import nbq.springboot.thymeleaf.lesson3.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @RequestMapping({"/products","/products/page/{pageNumber}"})
    public String listProducts(Model model, @PathVariable(required = false) Integer pageNumber,
                               @RequestParam(required = false) String sortField,
                               @RequestParam(required = false) String sortDir){
        sortField = (sortField != null && !sortField.trim().isEmpty()) ? sortField : "name";
        sortDir = (sortDir == null)? "asc" : (!sortDir.trim().equals("desc")) ? "asc" : "desc";
        String reverseSortDir = (sortDir != null && sortDir.trim().equals("asc")) ? "desc" : "asc";
        Sort sort = (sortDir.equals("asc")) ? Sort.by(Sort.Direction.ASC,sortField) : Sort.by(Sort.Direction.DESC,sortField);
        pageNumber = (pageNumber != null && pageNumber > 0) ? pageNumber : 1;
        Pageable pageable = PageRequest.of(pageNumber -1 ,3,sort);

        Page<Product> productPage = productRepo.findAll(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("totalPages",productPage.getTotalPages());
        model.addAttribute("totalItems",productPage.getTotalElements());
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);
        model.addAttribute("dirPath","products");
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
        if(nameDetails != null && nameDetails.length > 0 && valueDetails.length > 0){
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
