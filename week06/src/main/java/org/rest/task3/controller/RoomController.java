package org.rest.task3.controller;

import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.rest.task3.config.HibernateConfig;
import org.rest.task3.dao.RoomDAO;
import org.rest.task3.dto.RoomDTO;
import org.rest.task3.entities.Room;

import java.util.List;

public class RoomController {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static RoomDAO roomDAO = RoomDAO.getInstance(emf);
    @Transactional
    public static Handler getRooms()
    {
        return ctx -> {
            List<Room> result = roomDAO.getAll();
            if(result.isEmpty())
            {
                ctx.status(404).result("No hotels where found");
            }
            else
            {
                List<RoomDTO> roomDTOs = result.stream().map(room ->
                        RoomDTO.builder()
                                .id(room.getId())
                                .roomNumber(room.getRoomNumber())
                                .price(room.getPrice())
                                .hotelId(room.getHotel().getId())
                                .build()
                ).toList();
                ctx.json(roomDTOs);
            }
        };
    }
    public static Handler getRoomById()
    {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Room room = roomDAO.getById(id);
            RoomDTO roomDTO = new RoomDTO().builder()
                    .id(room.getId())
                    .roomNumber(room.getRoomNumber())
                    .price(room.getPrice())
                    .hotelId(room.getHotel().getId())
                    .build();
            ctx.json(roomDTO);
        };
    }
    public static Handler createRoom()
    {
        return ctx -> {
            Room newRoom = ctx.bodyAsClass(Room.class);
            roomDAO.create(newRoom);
            ctx.json(newRoom);
        };
    }
    public static Handler updateRoom()
    {
        return ctx -> {
            //Create new instance of a HotelDTO with new info
            RoomDTO newInfo = ctx.bodyAsClass(RoomDTO.class);
            System.out.println(newInfo);
            //Get id from path to find hotel that needs to be updated
            int id = Integer.parseInt(ctx.pathParam("id"));
            //Find hotel in DB by id
            Room room = roomDAO.getById(id);
            //Set new info on the persisted entity
            room.setRoomNumber(newInfo.getRoomNumber());
            room.setPrice(newInfo.getPrice());
            Room updatedRoom = roomDAO.update(room);
            ctx.json(updatedRoom);
        };
    }
    public static Handler deleteRoom()
    {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Room room = roomDAO.getById(id);
            roomDAO.delete(room);
            ctx.json(room);
        };
    }
}
