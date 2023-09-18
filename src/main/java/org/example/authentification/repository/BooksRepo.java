package org.example.authentification.repository;

import org.example.authentification.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepo extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM (SELECT *, row_number() OVER (ORDER BY id) as rn FROM books) as n  WHERE rn BETWEEN :from AND :to", nativeQuery = true)
    List<Book> getPage(int from, int to);
}
