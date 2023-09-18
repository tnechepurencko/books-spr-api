package org.example.authentification.repository;

import org.example.authentification.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRepo extends JpaRepository<Story, Long> {
    @Query("SELECT s FROM Story s WHERE s.bookId = :bookId")
    List<Story> getStories(long bookId);
}
