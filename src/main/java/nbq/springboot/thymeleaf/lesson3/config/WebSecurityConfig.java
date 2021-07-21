package nbq.springboot.thymeleaf.lesson3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
               .withUser("admin")
               .password("$2a$10$MQZ5cHvlpNDeMrJp/3xPWepJ0gUz0Lj0RpjZnjWZ1vtY./D/zWld.")
               .roles("ADMIN")
           .and()
               .withUser("nbquynh")
               .password("$2a$10$MQZ5cHvlpNDeMrJp/3xPWepJ0gUz0Lj0RpjZnjWZ1vtY./D/zWld.")
               .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/products").permitAll()
                .antMatchers("/products/new").hasAnyRole("USER", "ADMIN")
                .antMatchers("/products/edit/*", "/products/delete/*" ).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                ;
    }
}
