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
                    "The Crack",
                    "Mateo Paluci",
                    100,
                    "2008-3-12"

            );

            Book second = new Book(
                    "GOD",
                    "Coscu",
                    100,
                    "2008-3-12"
            );
            repository.saveAll(
                    List.of(first,second)
            );
        };
    }
}

