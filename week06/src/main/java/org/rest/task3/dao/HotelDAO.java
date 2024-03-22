package org.rest.task3.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.rest.task3.entities.Hotel;
import org.rest.task3.entities.Room;
import org.rest.task3.config.HibernateConfig;

import java.util.List;

public class HotelDAO extends DAO<Hotel, Integer>{
    private static EntityManagerFactory emf;
    private static HotelDAO instance;
//    public HotelDAO(EntityManagerFactory emf)
//    {
//        super(Hotel.class, emf);
//    }
    public static HotelDAO getInstance(EntityManagerFactory _emf)
    {
        if(instance == null)
        {
            emf = _emf;
            instance = new HotelDAO();
            instance.setClazz(Hotel.class);
        }
        return instance;
    }
    public List<Room> getAllRoomsByHotelId(int id)
    {
        List<Room> rooms;
        try(var em = emf.createEntityManager())
        {
            TypedQuery<Room> query = em.createQuery("select r from Room r join r.hotel h where h.id = ?1", Room.class);
            query.setParameter(1, id);
            rooms = query.getResultList();
        }
        return rooms;
    }
}
