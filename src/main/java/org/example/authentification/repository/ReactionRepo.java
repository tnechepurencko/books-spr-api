package org.example.authentification.repository;

import org.example.authentification.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepo extends JpaRepository<Reaction, Long> {
}
