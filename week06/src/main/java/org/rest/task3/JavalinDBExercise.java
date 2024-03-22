package org.rest.task3;

import jakarta.persistence.EntityManagerFactory;
import org.rest.task3.config.AppConfig;
import org.rest.task3.config.HibernateConfig;
import org.rest.task3.controller.HotelController;
import org.rest.task3.dao.HotelDAO;
import org.rest.task3.dao.UserDAO;
import org.rest.task3.entities.Room;
import org.rest.task3.entities.User;

import java.util.List;

public class JavalinDBExercise {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();
        appConfig
                .setup()
                .startServer()
                .checkSecurityRoles()
                .setRoute(Routes.getAuthRoutes())
                .setRoute(Routes.getProtectedRoutes())
                .setRoute(Routes.getHotelRoutes())
                .setRoute(Routes.getRoomRoutes());


        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        HotelDAO hotelDAO = HotelDAO.getInstance(emf);
        UserDAO userDAO = UserDAO.getInstance(emf);

//        Room one = new Room(11,200);
//        Room two = new Room(12,200);
//        Room three = new Room(13,200);
//        Room four = new Room(14,200);
//        Room five = new Room(15,500);
//        Hotel scandic = new Hotel("Scandic", "Vejen 230");
//        Hotel tivoli = new Hotel("Tivoli", "Tivolivej 2");
//
//        scandic.addRoom(one);
//        scandic.addRoom(two);
//        tivoli.addRoom(three);
//        tivoli.addRoom(four);
//        tivoli.addRoom(five);
//
//        hotelDAO.create(scandic);
//        hotelDAO.create(tivoli);
        List<Room> scandicRooms = hotelDAO.getAllRoomsByHotelId(3);
        //System.out.println(scandicRooms);
        User user = new User("lkh", "1234abcd");
        System.out.println(user.getPassword());
        //userDAO.createUser(user.getUsername(), user.getPassword());
    }
}
