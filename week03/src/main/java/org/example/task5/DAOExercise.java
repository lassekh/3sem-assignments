package org.example.task5;

import org.example.task2.Student;

public class DAOExercise {
    public static void main(String[] args) {
        Student student = new Student("Heino","Heinsen","heinos@mail.com", 40);

        StudentDAO dao = new StudentDAO();
        dao.create(student);

        dao.readAll().forEach(System.out::println);
    }
}
