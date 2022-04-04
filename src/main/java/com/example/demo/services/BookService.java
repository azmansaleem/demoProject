package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<Book> getAll() {
        List<Book> bookList = repository.findAll();

        if (bookList.size() > 0) {
            return bookList;
        } else {
            return new ArrayList<Book>();
        }
    }

    public Book getById(Long id) throws RecordNotFoundException {
        Optional<Book> book = repository.findById(id);

        if (book.isPresent()) {
            return book.get();
        } else {
            throw new RecordNotFoundException("No book record exist for given id");
        }
    }

    public Book createOrUpdateBook(Book entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Book> book = repository.findById(entity.getId());

            if (book.isPresent()) {
                Book newEntity = book.get();
                newEntity.setTitle(entity.getTitle());
                newEntity.setDescription(entity.getDescription());
                newEntity.setDepartment(entity.getDepartment());

                newEntity = repository.save(newEntity);

                return newEntity;
            }
        }
        entity = repository.save(entity);
        return entity;

    }

    public void deleteBookById(Long id) throws RecordNotFoundException {
        Optional<Book> book = repository.findById(id);

        if (book.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No book record exist for given id");
        }
    }
}
