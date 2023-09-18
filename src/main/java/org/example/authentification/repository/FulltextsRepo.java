package org.example.authentification.repository;

import org.example.authentification.entity.Fulltext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FulltextsRepo extends JpaRepository<Fulltext, Long>  {
    Optional<Fulltext> findById(Long id);
}
