package org.rest.task3.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {
    private int id;
    private int roomNumber;
    private int price;
    private int hotelId;
}
