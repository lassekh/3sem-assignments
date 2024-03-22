package org.rest.task3.controller;

import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.rest.task3.config.HibernateConfig;
import org.rest.task3.dao.HotelDAO;
import org.rest.task3.dto.HotelDTO;
import org.rest.task3.dto.RoomDTO;
import org.rest.task3.entities.Hotel;
import org.rest.task3.entities.Room;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HotelController {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static HotelDAO hotelDAO = HotelDAO.getInstance(emf);
    @Transactional
    public static Handler getHotels()
    {
        return ctx -> {
            List<Hotel> result = hotelDAO.getAll();
            if(result.isEmpty())
            {
                ctx.status(404).result("No hotels where found");
            }
            else
            {
                List<HotelDTO> hotelDTOs = result.stream().map(hotel ->
                        HotelDTO.builder()
                                .id(hotel.getId())
                                .name(hotel.getName())
                                .address(hotel.getAddress())
                                .build()
                ).toList();
                ctx.json(hotelDTOs);
            }
        };
    }
    public static Handler getHotelById()
    {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.getById(id);
            HotelDTO hotelDTO = new HotelDTO().builder()
                    .id(hotel.getId())
                    .name(hotel.getName())
                    .address(hotel.getAddress())
                    .build();
            ctx.json(hotelDTO);
        };
    }
    public static Handler getHotelRooms()
    {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            List<Room> result = hotelDAO.getAllRoomsByHotelId(id);
            if(result.isEmpty())
            {
                ctx.status(404).result("No rooms where found");
            } else
            {
                List<RoomDTO> roomDTOs = result.stream().map(room -> RoomDTO.builder()
                        .id(room.getId())
                        .roomNumber(room.getRoomNumber())
                        .price(room.getPrice())
                        .hotelId(room.getHotel().getId())
                        .build())
                        .toList();
                ctx.json(roomDTOs);
            }
        };
    }
    public static Handler createHotel()
    {
        return ctx -> {
            Hotel newHotel = ctx.bodyAsClass(Hotel.class);
            hotelDAO.create(newHotel);
            ctx.json(newHotel);
        };
    }
    public static Handler updateHotel()
    {
        return ctx -> {
            //Create new instance of a HotelDTO with new info
            HotelDTO newInfo = ctx.bodyAsClass(HotelDTO.class);
            System.out.println(newInfo);
            //Get id from path to find hotel that needs to be updated
            int id = Integer.parseInt(ctx.pathParam("id"));
            //Find hotel in DB by id
            Hotel hotel = hotelDAO.getById(id);
            //Set new info on the persisted entity
            if(newInfo.getName() != null) hotel.setName(newInfo.getName());
            if(newInfo.getAddress() != null) hotel.setAddress(newInfo.getAddress());
            Hotel updatedHotel = hotelDAO.update(hotel);
            ctx.json(updatedHotel);
        };
    }
    public static Handler deleteHotel()
    {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.getById(id);
            hotelDAO.delete(hotel);
            ctx.json(hotel);
        };
    }
}
