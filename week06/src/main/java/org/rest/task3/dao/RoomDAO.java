package org.rest.task3.dao;

import jakarta.persistence.EntityManagerFactory;
import org.rest.task3.config.HibernateConfig;
import org.rest.task3.entities.Hotel;
import org.rest.task3.entities.Room;

public class RoomDAO extends DAO<Room, Integer>{
    private static EntityManagerFactory emf;
    private static RoomDAO instance;
//    public RoomDAO(EntityManagerFactory emf)
//    {
//        super(Room.class, emf);
//    }
    public static RoomDAO getInstance(EntityManagerFactory _emf)
    {
        if(instance == null)
        {
            emf = _emf;
            instance = new RoomDAO();
            instance.setClazz(Room.class);
        }
        return instance;
    }
}
