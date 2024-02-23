package org.jpa.task2.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Driver {
    @Id
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
        String driverId = generateId(this.employmentDate);
        if(validateDriverId(driverId))
        {
            this.id = driverId;
        }
        else
        {
            System.out.println("What the fuck!?");
        }
    }
    public String generateId(LocalDate employmentDate)
    {
        String day = String.valueOf(employmentDate.getDayOfMonth());
        if(day.length() == 1) day = 0+day;
        String month = String.valueOf(employmentDate.getMonthValue());
        if(month.length() == 1) month = 0+month;
        String year = String.valueOf(employmentDate.getYear());
        String driverId = day + month + year.charAt(2) + year.charAt(3) + "-" + name.charAt(0) + surname.charAt(0) + "-" + (new Random().nextInt(899)+100) + surname.toUpperCase().charAt(surname.length()-1);
        return driverId;
    }
    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }
}
