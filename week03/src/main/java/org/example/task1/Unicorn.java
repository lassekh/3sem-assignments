package org.example.task1;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unicorn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class Unicorn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.PRIVATE)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "power_strength", nullable = false)
    private int powerStrength;

    public Unicorn(String name, int age, int powerStrength) {
        this.name = name;
        this.age = age;
        this.powerStrength = powerStrength;
    }
}
