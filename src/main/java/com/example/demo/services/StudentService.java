package com.example.demo.services;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public List<Student> getAll() {
        List<Student> studentList = repository.findAll();

        if (studentList.size() > 0) {
            return studentList;
        } else {
            return new ArrayList<Student>();
        }
    }

    public Student getById(Long id) throws RecordNotFoundException {
        Optional<Student> student = repository.findById(id);

        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RecordNotFoundException("No student record exist for given id");
        }
    }

    public Student createOrUpdateStudent(Student entity) throws RecordNotFoundException {


        if (entity.getId() != null) {
            Optional<Student> student = repository.findById(entity.getId());
            Student newEntity = student.get();
            newEntity.setName(entity.getName());
            newEntity.setEmail(entity.getEmail());
            newEntity.setDpt(entity.getDpt());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteStudentById(Long id) throws RecordNotFoundException {
        Optional<Student> student = repository.findById(id);

        if (student.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No student record exist for given id");
        }
    }
}
