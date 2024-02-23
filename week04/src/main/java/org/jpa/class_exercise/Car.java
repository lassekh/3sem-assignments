package org.jpa.class_exercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "company_id") //not necessary
    private Company company;
    public void addCompany(Company company)
    {
        if(company != null)
        {
            this.company = company;
        }
    }

}