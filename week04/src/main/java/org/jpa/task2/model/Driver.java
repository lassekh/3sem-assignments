package org.jpa.task2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue
    private String id;
    @Column(name = "employment_date")
    @Temporal(TemporalType.DATE)
    private LocalDate employmentDate;
    private String name;
    private String surname;
    private BigDecimal salary;
    @ManyToOne
    private WasteTruck wasteTruck;

    public Driver(String name, String surname, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }
    @PrePersist
    public void newEmployee()
    {
        this.employmentDate = LocalDate.now();
        String driverId = 223344 + name.charAt(0) + "-" + name.charAt(0) + "-" + (new Random().nextInt(899)+100) + surname.charAt(surname.length()-1);
        if(validateDriverId(driverId)) this.id = driverId;
    }
    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }
}
