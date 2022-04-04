package com.example.demo.controller;


import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get All Notes
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllBook() {
        List<Student> list = service.getAll();
        return new ResponseEntity<List<Student>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/")
    public String index() {
        return "Hello World student";
    }

    @RequestMapping("/count")
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from student", Integer.class);
    }


    @GetMapping("getById/{id}")
    public ResponseEntity<Student> getBookById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Student entity = service.getById(id);

        return new ResponseEntity<Student>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("createOrUpdate")
    public ResponseEntity<Student> createOrUpdateStudent(@RequestBody Student student)
            throws RecordNotFoundException {
        Student updated = service.createOrUpdateStudent(student);
        return new ResponseEntity<Student>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteStudentById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteStudentById(id);
        return HttpStatus.FORBIDDEN;
    }

}
