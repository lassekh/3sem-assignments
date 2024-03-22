package org.rest.task3.dto;

import lombok.*;
import org.rest.task3.entities.Room;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDTO {
    private int id;
    private String name;
    private String address;
    //private List<Room> rooms;
}
