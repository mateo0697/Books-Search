package com.example.demo.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // SELECT s FROM book s WHERE title = ?
    Optional<Book> findBookByTitle(String title);
    Optional<List<Book>> findAllByAuthor(String author, Pageable pageable);
    //-----------------------------------------------------
    Optional<List<Book>> findAllByPriceGreaterThanEqual(Integer price, Pageable pageable);
    Optional<List<Book>> findAllByPriceLessThanEqual(Integer price, Pageable pageable);
    Optional<List<Book>> findAllByPrice(Integer price, Pageable pageable);
    //--------------------------------------------------
    Optional<List<Book>> findAllByWriteGreaterThanEqual(LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByWriteLessThanEqual(LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByWrite(LocalDate write, Pageable pageable);
    //--------------------------------------------------
    Optional<List<Book>> findAllByAuthorAndPrice(String author, Integer price, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceLessThanEqual(String author, Integer price, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceGreaterThanEqual(String author, Integer price, Pageable pageable);
    //-----------------------------------------------------
    Optional<List<Book>> findAllByAuthorAndWrite(String author, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndWriteGreaterThanEqual(String author, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndWriteLessThanEqual(String author, LocalDate write, Pageable pageable);
    //----------------------------------------------------------------------
    Optional<List<Book>> findAllByPriceAndWrite(Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByPriceAndWriteLessThanEqual(Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByPriceAndWriteGreaterThanEqual(Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByPriceLessThanEqualAndWrite(Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByPriceGreaterThanEqualAndWrite(Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByPriceGreaterThanEqualAndWriteLessThanEqual(Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByPriceGreaterThanEqualAndWriteGreaterThanEqual(Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByPriceLessThanEqualAndWriteLessThanEqual(Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByPriceLessThanEqualAndWriteGreaterThanEqual(Integer price, LocalDate write, Pageable pageable);
    //--------------------------------------------------

    Optional<List<Book>> findAllByAuthorAndPriceAndWrite(String author, Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceAndWriteLessThanEqual(String author, Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceAndWriteGreaterThanEqual(String author, Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceLessThanEqualAndWrite(String author, Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceGreaterThanEqualAndWrite(String author, Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceLessThanEqualAndWriteLessThanEqual(String author, Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceLessThanEqualAndWriteGreaterThanEqual(String author, Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceGreaterThanEqualAndWriteLessThanEqual(String author, Integer price, LocalDate write, Pageable pageable);
    Optional<List<Book>> findAllByAuthorAndPriceGreaterThanEqualAndWriteGreaterThanEqual(String author, Integer price, LocalDate write, Pageable pageable);
}
