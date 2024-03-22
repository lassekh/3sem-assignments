package org.rest.task3.entities;

import jakarta.persistence.*;
import lombok.*;
import org.rest.task3.entities.Hotel;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_number")
    private int roomNumber;
    private int price;
    @ManyToOne
    private Hotel hotel;

    public Room(int roomNumber, int price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }
}
