package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This interface has all the methods necessary to fetch the data from the database
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query
    @Query("SELECT s from Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
