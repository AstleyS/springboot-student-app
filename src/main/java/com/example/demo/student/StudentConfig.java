package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner clrunner(StudentRepository repository) {
        return args -> {
            Student me = new Student(1L, "Astley", "a@hotmail.com", LocalDate.of(1997, Month.AUGUST, 18));
            repository.save(me);
        };

    }
}
