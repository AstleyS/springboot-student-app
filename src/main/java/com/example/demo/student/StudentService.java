package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

// Business Layer: In this class we have all our logic
@Service
public class StudentService {
    public List<Student> getStudents() {
        return Arrays.asList(
                new Student(1L, "Astley", "a@hotmail.com", LocalDate.of(1997, Month.AUGUST, 18))
        );
    }
}
