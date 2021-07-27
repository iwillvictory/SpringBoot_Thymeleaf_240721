package nbq.springboot.thymeleaf.lesson3.config;

import nbq.springboot.thymeleaf.lesson3.handlerException.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
               .withUser("admin")
               //pass: QuynhVan
               .password("$2a$10$MQZ5cHvlpNDeMrJp/3xPWepJ0gUz0Lj0RpjZnjWZ1vtY./D/zWld.")
               .roles("ADMIN")
           .and()
               .withUser("nbquynh")
               //pass: QuynhVan
               .password("$2a$10$MQZ5cHvlpNDeMrJp/3xPWepJ0gUz0Lj0RpjZnjWZ1vtY./D/zWld.")
               .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Các đường dẫn của các file tĩnh phải được thêm vào permit ALl , để trang dùng tài nguyên tĩnh có thể chạy bình thường mà không phải login
                .antMatchers("/","/index","/products","/webjars/**").permitAll()
                .antMatchers("/products/new").hasAnyRole("USER", "ADMIN")
                .antMatchers("/products/edit/*", "/products/delete/*" ).hasRole("ADMIN")
                .anyRequest().authenticated()
                /** HTTP Basic Authentication
                .and()
                .httpBasic()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                ;*/
                .and()
            .formLogin()
                .loginPage("/login")

                //Default URL sử lý khi login : /login , trong action attribute của <form>  Ta có thể custom URL bằng: loginProcessingUrl("customURL")
                // Phải điều chỉnh giá trị tương ứng với thuộc tính action của <form> trong login page.
                /*.loginProcessingUrl("doLogin")*/

                // giá trị mặc định của trường name của form login  là : username, password. Ta có thể tùy chỉnh điều này bằng 2 phương thức dưới đây
                /*.usernameParameter("custom_username")
                .passwordParameter("custom_password")*/
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .exceptionHandling()
                // Khi thực hiện thêm việc lưu log lại các lỗi ,hoặc làm thêm một vài tác vụ nào đó khi lỗi xảy ra, ta có thể thực thi  custom AccessDeniedHandler
                .accessDeniedHandler(customAccessDeniedHandler)
            ;
    }
}
