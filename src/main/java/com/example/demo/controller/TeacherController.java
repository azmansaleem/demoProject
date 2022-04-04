package com.example.demo.controller;


import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Teacher;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService service;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get All Notes
    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllBook() {
        List<Teacher> list = service.getAll();
        return new ResponseEntity<List<Teacher>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/")
    public String index() {
        return "Hello World teacher";
    }

    @RequestMapping("/count")
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from student", Integer.class);
    }


    @GetMapping("getById/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Teacher entity = service.getById(id);

        return new ResponseEntity<Teacher>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("createOrUpdate")
    public ResponseEntity<Teacher> createOrUpdateTeacher(@RequestBody Teacher teacher)
            throws RecordNotFoundException {
        Teacher updated = service.createOrUpdateBook(teacher);
        return new ResponseEntity<Teacher>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteStudentById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteTeacherById(id);
        return HttpStatus.FORBIDDEN;
    }

}
