package com.Amsall.amsallApp.config;

import com.Amsall.amsallApp.models.Role;
import com.Amsall.amsallApp.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepo) {
        return args -> {
            if (roleRepo.findByName("ROLE_ADMIN").isEmpty()) {
                roleRepo.save(new Role("ROLE_ADMIN"));
            }
            if (roleRepo.findByName("ROLE_USER").isEmpty()) {
                roleRepo.save(new Role("ROLE_USER"));
            }
        };
    }
}
