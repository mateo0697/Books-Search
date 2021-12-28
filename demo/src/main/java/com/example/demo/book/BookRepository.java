package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // SELECT s FROM book s WHERE title = ?
    Optional<Book> findBookByTitle(String title);
}
