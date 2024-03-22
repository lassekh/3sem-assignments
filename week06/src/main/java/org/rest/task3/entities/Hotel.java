package org.rest.task3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Room> rooms = new HashSet<>();

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addRoom(Room room)
    {
        this.rooms.add(room);
        if(room != null)
        {
            room.setHotel(this);
        }
    }
}
