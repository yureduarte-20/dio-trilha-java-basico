package com.yure.complaints.application.seeders;

import com.yure.complaints.domain.models.Roles;
import com.yure.complaints.domain.models.User;
import com.yure.complaints.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeed implements CommandLineRunner {
    @Value("${spring.security.user.name}")
    private String adminEmail;
    @Value("${spring.security.user.password}")
    private String adminPassword;

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    public UserSeed(PasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if(this.userRepository.count() > 0) return;
        var user = new User();
        user.setEmail(this.adminEmail);
        user.setName(this.adminEmail);
        user.setRole(Roles.ADMIN);
        user.setPassword(bCryptPasswordEncoder.encode(adminPassword));
        this.userRepository.save(user);
    }
}
