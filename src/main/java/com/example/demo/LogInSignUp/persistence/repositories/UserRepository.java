package com.example.demo.LogInSignUp.persistence.repositories;

import com.example.demo.LogInSignUp.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
