package com.example.demo.LogInSignUp.persistence.repositories;

import com.example.demo.LogInSignUp.persistence.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
