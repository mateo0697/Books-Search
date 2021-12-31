package com.example.demo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository){
        return args -> {
            Book first = new Book(
                    "Book 1",
                    "Mateo Paluci",
                    "150",
                    "12-3-2008"

            );

            Book second = new Book(
                    "Book 2",
                    "Sondeos",
                    "200",
                    "21-5-2034"
            );
            repository.saveAll(
                    List.of(first,second)
            );
        };
    }
}
