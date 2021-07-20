package nbq.springboot.thymeleaf.lesson3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping({"","index"})
    public String viewHomePage(){
        return "index";
    }
}
