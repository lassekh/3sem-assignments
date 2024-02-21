package org.jpa.task1;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //1-1 relation with PersonalDetails
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonalDetails personalDetails;
    //1-* relation with Fee
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Fee> fees = new HashSet<>();
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Note> notes = new HashSet<>();

    public Person(String name)
    {
        this.name = name;
    }
    public void addPersonalDetails(PersonalDetails pd)
    {
        this.personalDetails = pd;
        if(pd != null)
        {
            personalDetails.setPerson(this);
        }
    }
    public void addFee(Fee fee)
    {
        this.fees.add(fee);
        if(fee != null)
        {
            fee.setPerson(this);
        }
    }

    public void addNote(Note note)
    {
        this.notes.add(note);
        if(note != null)
        {
            note.setPerson(this);
        }
    }
}
