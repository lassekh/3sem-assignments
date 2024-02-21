package org.jpa.task1;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "person")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String note;
    private LocalDate createdAt;
    @ManyToOne
    private Person person;

    public Note(String note, LocalDate createdAt) {
        this.note = note;
        this.createdAt = createdAt;
    }
}
