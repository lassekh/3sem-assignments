package org.jpa.task1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "person")
public class PersonalDetails {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private int zip;
    private String city;
    private int age;
    @OneToOne
    @MapsId
    private Person person;

    public PersonalDetails(String address, int zip, String city, int age) {
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.age = age;
    }
}
