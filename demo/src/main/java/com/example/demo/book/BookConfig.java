package com.example.demo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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
                    100,
                    LocalDate.of(2020,Month.MARCH, 12)
            );

            Book second = new Book(
                    "Book 2",
                    "Sondeos",
                    200,
                    LocalDate.of(2010,Month.FEBRUARY, 27)
            );
            repository.saveAll(
                    List.of(first,second)
            );
        };
    }
}

