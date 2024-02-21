package org.jpa.task2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "waste_truck")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WasteTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private int capacity;
    @Column(name = "is_available")
    private boolean isAvailable;
    @Column(name = "registration_number", unique = true)
    private String registrationNumber;
    @OneToMany(mappedBy = "wasteTruck", cascade = CascadeType.PERSIST)
    private Set<Driver> drivers = new HashSet<>();

    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }
}
