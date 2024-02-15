package org.example.task6;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StudentDAO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    public void create(){

    }
    public void read(){

    }
    public void update(){

    }
    public void delete(){

    }
}
