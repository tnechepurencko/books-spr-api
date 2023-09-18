package org.example.authentification.repository;

import org.example.authentification.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
