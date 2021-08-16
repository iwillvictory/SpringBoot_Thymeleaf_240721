package nbq.springboot.thymeleaf.lesson3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("/error/403");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path imageDir = Paths.get("images");
        String imagePath = imageDir.toFile().getAbsolutePath();
        String path = "file:/" + imagePath + "/";
        registry.addResourceHandler("/images/**").addResourceLocations(path);
//        registry.addResourceHandler("/images/**").addResourceLocations("file:/" + imagePath + "/");
    }
}
