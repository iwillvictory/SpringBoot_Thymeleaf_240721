package nbq.springboot.thymeleaf.lesson3.controllers;

import nbq.springboot.thymeleaf.lesson3.models.Product;
import nbq.springboot.thymeleaf.lesson3.models.Role;
import nbq.springboot.thymeleaf.lesson3.models.User;
import nbq.springboot.thymeleaf.lesson3.repositories.RoleRepository;
import nbq.springboot.thymeleaf.lesson3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @ModelAttribute
    public void getListOfRoles(Model model){
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("rolesList", roles);
    }

    @RequestMapping({"/users","/users/page/{pageNumber}"})
    public String listUsers(Model model, @PathVariable(required = false) Integer pageNumber,
                               @RequestParam(required = false) String sortField,
                               @RequestParam(required = false) String sortDir){
        // processing inputs for pagination
        sortField = (sortField != null && !sortField.trim().isEmpty()) ? sortField : "name";
        sortDir = (sortDir == null)? "asc" : (!sortDir.trim().equals("desc")) ? "asc" : "desc";
        pageNumber = (pageNumber != null && pageNumber > 0) ? pageNumber : 1;
        String reverseSortDir = (sortDir != null && sortDir.trim().equals("asc")) ? "desc" : "asc";
        //prepare get page of User
        Sort sort = (sortDir.equals("asc")) ? Sort.by(Sort.Direction.ASC,sortField) : Sort.by(Sort.Direction.DESC,sortField);
        Pageable pageable = PageRequest.of(pageNumber -1 ,5,sort);
        //get page of User
        Page<User> userPage = userRepo.findAll(pageable);

        //send data to view
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("totalPages",userPage.getTotalPages());
        model.addAttribute("totalItems",userPage.getTotalElements());
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);
        model.addAttribute("dirPath","users");
        return "user/UserManager";

    }

    // navigate to user form
    @GetMapping("/users/new")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("title","Create New User");
        return "user/UserForm";
    }

    // navigate to user form
    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        Optional<User> userOptional = userRepo.findById(id);
        User user = new User();
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        model.addAttribute("user", user);
        model.addAttribute("title","Edit User");
        return "user/UserForm";
    }

    //Save user
    @PostMapping("/users/save")
    public String saveUser(User user){
       userRepo.save(user);
       return "redirect:/users";
    }

}
