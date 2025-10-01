package com.chickendelivery;

import com.chickendelivery.model.User;
import com.chickendelivery.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ChickenDeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChickenDeliveryApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User(
                        "admin",
                        passwordEncoder.encode("admin"),
                        "admin@example.com",
                        "address",
                        true);
                userRepository.save(admin);
                System.out.println("Default admin user created.");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}