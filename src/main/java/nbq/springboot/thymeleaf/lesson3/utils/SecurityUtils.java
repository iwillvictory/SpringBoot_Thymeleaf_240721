package nbq.springboot.thymeleaf.lesson3.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "QuynhVan";  // $2a$10$MQZ5cHvlpNDeMrJp/3xPWepJ0gUz0Lj0RpjZnjWZ1vtY./D/zWld.
                                            //$2a$10$DXJ7RrcUMoZk2xcQhlA9WOfZxJm0snUXBi3c5KXtLhunG2zQwxoRC
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        System.out.println("|" + encodedPassword + "|");
    }
}
