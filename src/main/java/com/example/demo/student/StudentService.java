package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// Business Layer: In this class we have all our logic
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail =
                studentRepository.findStudentByEmail(student.getEmail());

        // Check if student to add has a valid email
        if (studentByEmail.isPresent())
            throw new IllegalStateException("Email taken");

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);

        if (!studentExists)
            throw new IllegalStateException("Student with id" + studentId + "does not exists");

        studentRepository.deleteById(studentId);
    }

    // Entity goes in management state
    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id" + id + " does not exists"));

        // If name not null, not empty and not the same as previous, update the name
        if (name != null && name.length() >0 && !student.getName().equals(name)) {
            student.setName(name);
        } else {
            throw new IllegalStateException("Invalid name parameter");
        }

        if (email != null && email.length() >0 && !student.getEmail().equals(email)) {

            Optional<Student> studentByEmail =
                    studentRepository.findStudentByEmail(email);

            // Check if email to update is unique
            if (studentByEmail.isPresent())
                throw new IllegalStateException("Email taken");

            student.setEmail(email);
        } else {
            throw new IllegalStateException("Invalid email parameter");
        }
    }
}
