package com.example.demo.services;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Teacher;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TeacherService {

    @Autowired
    TeacherRepository repository;

    public List<Teacher> getAll() {
        List<Teacher> teacherList = repository.findAll();
        if (teacherList.size() > 0) {
            return teacherList;
        } else {
            return new ArrayList<Teacher>();
        }
    }

    public Teacher getById(Long id) throws RecordNotFoundException {
        Optional<Teacher> teacher = repository.findById(id);

        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new RecordNotFoundException("No teacher record exist for given id");
        }
    }

    public Teacher createOrUpdateBook(Teacher entity) throws RecordNotFoundException {


        if (entity.getId() != null) {
            Optional<Teacher> teacher = repository.findById(entity.getId());
            Teacher newEntity = teacher.get();
            newEntity.setName(entity.getName());
            newEntity.setEmail(entity.getEmail());
            newEntity.setDetail(entity.getDetail());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteTeacherById(Long id) throws RecordNotFoundException {
        Optional<Teacher> teacher = repository.findById(id);

        if (teacher.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No teacher record exist for given id");
        }
    }
}
