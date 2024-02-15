package org.example.task2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "student")
@NoArgsConstructor
@ToString
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "age")
    private int age;

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    @PrePersist
    public void verifyNewEmail(){
        if (!this.email.contains("@")) {
            throw new RuntimeException("incorrect email address");
        }
    }
    @PreUpdate
    public void verifyUpdatedEmail(){
        if (!this.email.contains("@")) {
            throw new RuntimeException("incorrect email address");
        }
    }
}
