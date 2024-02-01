package org.app;

import java.time.LocalDate;
import java.util.Date;

public class Employee {
    private String name;
    private int age;
    private LocalDate birthDate;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Employee(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                '}';
    }
}
