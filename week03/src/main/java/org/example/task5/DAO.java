package org.example.task5;

import org.example.task2.Student;

import java.util.List;

public interface DAO {
    void create(Student s);
    Object read(int id);
    Object update(Student s);
    void delete(int id);
    List<Student> readAll();

}
