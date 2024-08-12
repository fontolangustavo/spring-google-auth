package com.fontolan.spring.google.auth.repositories;

import com.fontolan.spring.google.auth.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}