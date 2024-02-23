package org.jpa.class_exercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST)
    private Set<Car> cars = new HashSet<>();
    public void addCar(Car car)
    {
        if(car != null)
        {
            this.cars.add(car);
            car.setCompany(this);
        }
    }
}