package com.example.demo.controller;


import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // Get All Notes
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        System.out.println("get all notes....");
        return noteRepository.findAll();
    }
    @RequestMapping("/")
    public String index()
    {
        return "Hello World kk";
    }

    @RequestMapping("/count")
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from note", Integer.class);
    }

}
