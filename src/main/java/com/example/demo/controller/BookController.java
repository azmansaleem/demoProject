package com.example.demo.controller;


import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Note;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get All Notes
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> list = service.getAll();
        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/")
    public String index() {
        return "Hello World book";
    }

    @RequestMapping("/count")
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from note", Integer.class);
    }




    @GetMapping("getById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Book entity = service.getById(id);

        return new ResponseEntity<Book>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createOrUpdateBook(@RequestBody Book book)
            throws RecordNotFoundException {
        Book updated = service.createOrUpdateBook(book);
        return new ResponseEntity<Book>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("deleteById/{id}")
    public HttpStatus deleteBookById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteBookById(id);
        return HttpStatus.FORBIDDEN;
    }

}
