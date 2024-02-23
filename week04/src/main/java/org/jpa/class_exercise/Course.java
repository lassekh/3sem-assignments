package org.jpa.class_exercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    @ManyToMany
    private Set<Student> students = new HashSet<>();
    public void addStudent(Student student)
    {
        if(student != null)
        {
            this.students.add(student);
        }
    }
}