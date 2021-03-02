package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class has all of the resources for all of our API
// This annotation makes this class to serve RESTful endpoint
@RestController
@RequestMapping(path = "/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    // Get all students
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    // Add a student
    public void registerStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "/delete/{studentId}")
    // Delete a student
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/update/{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String email
    ) {
        studentService.updateStudent(studentId, name, email);
    }


}
